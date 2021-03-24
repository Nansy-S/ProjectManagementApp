package com.prokopovich.projectmanagement.controller;

import com.prokopovich.App;
import com.prokopovich.projectmanagement.enumeration.AccountActionType;
import com.prokopovich.projectmanagement.enumeration.UserRole;
import com.prokopovich.projectmanagement.factory.MySqlServiceFactory;
import com.prokopovich.projectmanagement.factory.ServiceFactory;
import com.prokopovich.projectmanagement.model.Account;
import com.prokopovich.projectmanagement.model.User;
import com.prokopovich.projectmanagement.service.AccountService;
import com.prokopovich.projectmanagement.service.UserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Scanner;

public class AccountController {

    private static final Logger LOGGER = LogManager.getLogger(App.class);
    private static final Scanner INPUT = new Scanner(System.in);

    private static ServiceFactory service = new MySqlServiceFactory();
    private static UserService userService = service.getUserServiceImpl();
    private static AccountService accountService = service.getAccountServiceImpl();

    public List<Account> displayUsersByReporter() {
        List<Account> userAccounts;
        int number = 0;

        userAccounts = accountService.getAllByReporterAndAction(
                App.getCurrentUser().getAccountId(), AccountActionType.CREATE.getTitle());
        System.out.println(" #) email - role ");
        for(Account userAccount: userAccounts) {
            number++;
            System.out.println(number + ") " +
                    userAccount.getEmail() + " - " +
                    userAccount.getRole());
        }
        return userAccounts;
    }

    public void addUser() {
        Account newAccount = new Account();
        User newUser = new User();

        System.out.println("Enter new user: ");
        System.out.print("\tsurname: ");
        newAccount.setSurname(INPUT.nextLine());
        newAccount.setSurname(INPUT.nextLine());
        System.out.print("\tname: ");
        newAccount.setName(INPUT.nextLine());
        System.out.print("\tpatronymic: ");
        newAccount.setPatronymic(INPUT.nextLine());
        System.out.print("\temail: ");
        newAccount.setEmail(INPUT.nextLine());
        System.out.print("\tpassword: ");
        newAccount.setPassword(INPUT.nextLine());
        newAccount.setRole(enterUserRole());
        System.out.print("\tposition: ");
        newUser.setPosition(INPUT.nextLine());
        newUser.setPosition(INPUT.nextLine());
        System.out.print("\tphone: ");
        newUser.setPhone(INPUT.nextLine());
        System.out.print("Enter a reason to add new account: ");
        String reason = INPUT.nextLine();
        Account addedAccount = accountService.addNewAccount(newAccount, newUser, reason);
        LOGGER.debug(addedAccount.toString());
    }

    public String enterUserRole() {
        String role = "role";

        System.out.print("choose role: \n\t\t1) " + UserRole.MANAGER.getTitle() +
                "\n\t\t2) " + UserRole.DEVELOPER.getTitle() +
                "\n\t\t3) " + UserRole.TESTER.getTitle() + "\n");
        int chosenRole = INPUT.nextInt();
        switch (chosenRole) {
            case 1:
                role = UserRole.MANAGER.getTitle();
                break;
            case 2:
                role = UserRole.DEVELOPER.getTitle();
                break;
            case 3:
                role = UserRole.TESTER.getTitle();
                break;
            default:
                System.out.println("Invalid character! Try again.");
                break;
        }
        return role;
    }

    public void editUser() {
        List<Account> userAccounts = displayUsersByReporter();
        System.out.println("Select a user to edit:");
        int chosenUserAccount = INPUT.nextInt() - 1;
        Account account = userAccounts.get(chosenUserAccount);
        User user = userService.getByUserId(account.getAccountId());
        System.out.println("User info:" +
                "\n\tuserId: " + user.getUserId() +
                "\n\temail: " + user.getAccountInfo().getEmail() +
                "\n\tsurname: " + user.getAccountInfo().getSurname() +
                "\n\tname: " + user.getAccountInfo().getName() +
                "\n\tpatronymic: " + user.getAccountInfo().getPatronymic() +
                "\n\trole: " + user.getAccountInfo().getRole() +
                "\n\tposition: " + user.getPosition() +
                "\n\tphone: " + user.getPhone());
        System.out.println("Do you want to edit information about this user? (1 - Yes, 2 - No)");
        int choice = INPUT.nextInt();
        if (choice == 1) {
            System.out.println("Select a field to edit:");
            System.out.println("1) email \n2) surname \n3) name \n4) patronymic "+
                    "\n5) position \n6) phone \nYour choice: ");
            int chosenField = INPUT.nextInt();
            String newFieldValue = INPUT.nextLine();
            newFieldValue = INPUT.nextLine();
            switch (chosenField) {
                case 1:
                    account.setEmail(newFieldValue);
                    break;
                case 2:
                    account.setSurname(newFieldValue);
                    break;
                case 3:
                    account.setName(newFieldValue);
                    break;
                case 4:
                    account.setPatronymic(newFieldValue);
                    break;
                case 5:
                    user.setPosition(newFieldValue);
                    break;
                case 6:
                    user.setPhone(newFieldValue);
                    break;
                default:
                    System.out.println("Invalid character! Try again.");
                    break;
            }
            System.out.print("Enter a reason to edit: ");
            String reason = INPUT.nextLine();
            accountService.editAccount(account, user, reason);
        }
    }

    public void findAccount() {
        Account account = accountService.getByAccountId(26);
        LOGGER.debug(account.toString());
    }
}
