package com.prokopovich.projectmanagement.webapp.controller;

import com.prokopovich.App;
import com.prokopovich.projectmanagement.enumeration.AccountActionType;
import com.prokopovich.projectmanagement.enumeration.AccountStatus;
import com.prokopovich.projectmanagement.enumeration.UserRole;
import com.prokopovich.projectmanagement.factory.ServiceFactoryImpl;
import com.prokopovich.projectmanagement.factory.ServiceFactory;
import com.prokopovich.projectmanagement.model.Account;
import com.prokopovich.projectmanagement.model.AccountAction;
import com.prokopovich.projectmanagement.model.User;
import com.prokopovich.projectmanagement.service.AccountService;
import com.prokopovich.projectmanagement.service.UserService;
import com.prokopovich.projectmanagement.util.ObjectFormat;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Scanner;

public class AccountController {

    private static final Logger LOGGER = LogManager.getLogger(AccountController.class);
    private static final Scanner INPUT = new Scanner(System.in);

    private final ServiceFactory service = new ServiceFactoryImpl();
    private final UserService userService = service.getUserService();
    private final AccountService accountService = service.getAccountService();

    public Account displayUsersByReporter() {
        List<Account> userAccounts;
        Account account = new Account();
        int number = 0;

        LOGGER.trace("displayUsersByReporter method is executed");
        userAccounts = accountService.getAllCreatedUser(
                App.getCurrentUser().getAccountId(), AccountActionType.CREATE.getTitle());
        System.out.println(" #) email - role ");
        for(Account userAccount: userAccounts) {
            number++;
            System.out.println(number + ") " +
                    userAccount.getEmail() + " - " +
                    userAccount.getRole());
        }
        System.out.print("Enter record number to view (edit) detailed information or 0 to exit: ");
        int chosenUserAccount = INPUT.nextInt();
        if (chosenUserAccount != 0) {
            chosenUserAccount--;
            account = userAccounts.get(chosenUserAccount);
            displayUserInfo(account.getAccountId());
        }
        return account;
    }

    public void displayUserInfo(int id) {
        int numberAction = 0;

        LOGGER.trace("displayUserInfo method is executed");
        User user = userService.getByUserId(id);
        System.out.println("User info:" +
                "\n\tuserId: " + user.getUserId() +
                "\n\temail: " + user.getAccountInfo().getEmail() +
                "\n\tsurname: " + user.getAccountInfo().getSurname() +
                "\n\tname: " + user.getAccountInfo().getName() +
                "\n\tpatronymic: " + user.getAccountInfo().getPatronymic() +
                "\n\tcurrent status: " + user.getCurrentStatus() +
                "\n\trole: " + user.getAccountInfo().getRole() +
                "\n\tposition: " + user.getPosition() +
                "\n\tphone: " + user.getPhone());
        System.out.println("Actions: ");
        for (AccountAction action : user.getAccountActions()) {
            numberAction++;
            System.out.println("\t" + numberAction +
                    ") " + action.getAction().getType() +
                    " - " + ObjectFormat.formattingDateTime(action.getAction().getDatetime()) +
                    " - " + action.getReason() +
                    " - " + action.getAction().getReporterInfo().getName() +
                    " " + action.getAction().getReporterInfo().getPatronymic() +
                    " " + action.getAction().getReporterInfo().getSurname()
            );
        }
    }

    public void addUser() {
        Account newAccount = new Account();
        User newUser = new User();

        LOGGER.trace("addUser method is executed");
        System.out.println("Enter new user: ");
        System.out.print("\tsurname: ");
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
        accountService.addNewAccount(newAccount, App.getCurrentUser(), newUser, reason);
        LOGGER.trace("new user added");
    }

    public void editUser() {
        LOGGER.trace("editUser method is executed");
        Account account = displayUsersByReporter();
        User user = userService.getByUserId(account.getAccountId());
        System.out.println("Do you want to edit information about this user? (1 - Yes, 2 - No)");
        int choice = INPUT.nextInt();
        if (choice == 1) {
            LOGGER.trace("old user information - " + user.toString());
            System.out.println("Select a field to edit:");
            System.out.println("1) email \n2) surname \n3) name \n4) patronymic "+
                    "\n5) position \n6) phone \nYour choice: ");
            int chosenField = INPUT.nextInt();
            System.out.print("Enter a new value: ");
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
            if(accountService.editAccount(account, App.getCurrentUser(), user, reason)) {
                LOGGER.trace("new user information - " + user.toString());
                LOGGER.trace("user information successfully edited");
            }
        } else {
            LOGGER.trace("cancel editing");
        }
    }

    public void changeUserStatus() {
        String typeAction = "action";

        LOGGER.trace("changeUserStatus method is executed");
        Account account = displayUsersByReporter();
        User user = userService.getByUserId(account.getAccountId());
        System.out.print("Current status: " + user.getCurrentStatus() +
                "\nChange user status? (1 - Yes, 2 - No): ");
        int choice = INPUT.nextInt();
        if (choice == 1) {
            System.out.print("Choose action:\n" +
                    "1) Block user \n2) Unblock user \n3) Deactivate user \nYour choice: ");
            int choiceAction = INPUT.nextInt();
            switch (choiceAction) {
                case 1:
                    user.setCurrentStatus(AccountStatus.LOCKED.getTitle());
                    typeAction = AccountActionType.BLOCK.getTitle();
                    break;
                case 2:
                    user.setCurrentStatus(AccountStatus.ACTIVE.getTitle());
                    typeAction = AccountActionType.UNBLOCK.getTitle();
                    break;
                case 3:
                    user.setCurrentStatus(AccountStatus.DEACTIVATED.getTitle());
                    typeAction = AccountActionType.DEACTIVATION.getTitle();
                    break;
                default:
                    System.out.println("Invalid character! Try again.");
                    break;
            }
            System.out.print("Enter a reason to change user status: ");
            String reason = INPUT.nextLine();
            reason = INPUT.nextLine();
            if(accountService.changeStatus(user, App.getCurrentUser(), reason, typeAction)) {
                LOGGER.trace("new user status - " + user.getCurrentStatus());
                LOGGER.trace("user status successfully edited");
            }
        } else {
            LOGGER.trace("cancel editing");
        }
    }

    public void changeUserRole() {
        LOGGER.trace("changeUserRole method is executed");
        Account account = displayUsersByReporter();
        System.out.print("Current role: " + account.getRole() +
                "\nChange user role? (1 - Yes, 2 - No): ");
        int choice = INPUT.nextInt();
        if (choice == 1) {
            account.setRole(enterUserRole());
            System.out.print("Enter a reason to change role: ");
            String reason = INPUT.nextLine();
            reason = INPUT.nextLine();
            if(accountService.changeRole(account, App.getCurrentUser(), reason)) {
                LOGGER.trace("new user role - " + account.getRole());
                LOGGER.trace("user role successfully edited");
            } else {
                LOGGER.trace("cancel editing");
            }
        }
    }

    public String enterUserRole() {
        String role = "role";

        System.out.println("choose role: \n\t\t1) " + UserRole.MANAGER.getTitle() +
                "\n\t\t2) " + UserRole.DEVELOPER.getTitle() +
                "\n\t\t3) " + UserRole.TESTER.getTitle());
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
}
