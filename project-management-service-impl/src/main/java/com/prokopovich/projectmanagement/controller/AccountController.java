package com.prokopovich.projectmanagement.controller;

import com.prokopovich.App;
import com.prokopovich.projectmanagement.enumeration.AccountActionType;
import com.prokopovich.projectmanagement.enumeration.UserRole;
import com.prokopovich.projectmanagement.factory.MySqlServiceFactory;
import com.prokopovich.projectmanagement.factory.ServiceFactory;
import com.prokopovich.projectmanagement.model.Account;
import com.prokopovich.projectmanagement.model.AccountAction;
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

    public static void displayUsersByReporter() {
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
    }

    public static void addUser() {
        Account newAccount = new Account();
        User newUser = new User();
        AccountAction newAccountAction = new AccountAction();

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
        System.out.print("\treason: ");
        newAccountAction.setReason(INPUT.nextLine());
        Account addedAccount = accountService.addNewAccount(newAccount, newUser, newAccountAction);
        LOGGER.debug(addedAccount.toString());
    }

    public static String enterUserRole() {
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

    public static void editUser() {
        System.out.println("Select a user to edit:");

    }

    public static void findAccount() {
        Account account = accountService.getByAccountId(26);
        LOGGER.debug(account.toString());
    }
}
