package com.prokopovich;

import com.prokopovich.projectmanagement.enumeration.UserRole;
import com.prokopovich.projectmanagement.factory.MySqlServiceFactory;
import com.prokopovich.projectmanagement.factory.ServiceFactory;
import com.prokopovich.projectmanagement.model.Account;
import com.prokopovich.projectmanagement.model.AccountAction;
import com.prokopovich.projectmanagement.model.Action;
import com.prokopovich.projectmanagement.model.User;
import com.prokopovich.projectmanagement.service.AccountService;
import com.prokopovich.projectmanagement.service.UserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.time.LocalDateTime;
import java.util.Scanner;

public class App {

    private static final Logger LOGGER = LogManager.getLogger(App.class);
    private static final Scanner INPUT = new Scanner(System.in);

    private static Account currentUser = new Account();
    private static UserRole currentUserRole;
    private static ServiceFactory service = new MySqlServiceFactory();
    private static UserService userService = service.getUserServiceImpl();
    private static AccountService accountService = service.getAccountServiceImpl();


    public static void main( String[] args ) {
        authorization();
    }

    public static Account getCurrentUser(){
        return currentUser;
    }

    private static void authorization() {
        System.out.print("Enter your email: ");
        String login = INPUT.nextLine();
        System.out.print("Enter your password: ");
        String password = INPUT.nextLine();
        currentUser = accountService.authorization(login, password);
        if (currentUser != null) {
            System.out.println("You entered as " + currentUser.getRole());
            currentUserRole = UserRole.fromString(currentUser.getRole());
            switch (currentUserRole) {
                case ADMIN:
                    menuAdmin();
                    break;
            }
        }
    }

    public static void addUser() {
        Account newAccount = new Account();
        User newUser = new User();
        Action newAction = new Action();
        AccountAction newAccountAction = new AccountAction();
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
        System.out.print("choose role: \n\t\t1) " + UserRole.MANAGER.getTitle() +
                "\n\t\t2) " + UserRole.DEVELOPER.getTitle() +
                "\n\t\t3) " + UserRole.TESTER.getTitle());
        while (!INPUT.hasNextInt()) {
            System.out.println("Re-enter without letters. Your choice: ");
            INPUT.next();
        }
        int chosenRole = INPUT.nextInt();
        switch (chosenRole) {
            case 1:
                newAccount.setRole(UserRole.MANAGER.getTitle());
                break;
            case 2:
                newAccount.setRole(UserRole.DEVELOPER.getTitle());
                break;
            case 3:
                newAccount.setRole(UserRole.TESTER.getTitle());
                break;
            default:
                System.out.println("Invalid character! Try again.");
                break;
        }
        System.out.print("\tposition: ");
        newUser.setPosition(INPUT.nextLine());
        System.out.print("\tphone: ");
        newUser.setPhone(INPUT.nextLine());
        System.out.print("\treason: ");
        newAccountAction.setReason(INPUT.nextLine());




        //Account newAccount = new Account(0, "Ivan", "Ivanov", "Ivanovich", "i@mail.ru", "1111", "Manager", null);
        //User newUser = new User(0, "Manager", "Active", "12345", newAccount);
        //Account addedAccount = accountService.addNewAccount(newAccount, newUser);
        //LOGGER.debug(addedAccount.toString());
    }

    public static void findAccount() {
        Account account = accountService.getByAccountId(26);
        LOGGER.debug(account.toString());
    }

    public static void menuAdmin(){
        int choice;
        boolean menuFlag = true;
        while (menuFlag) {
            System.out.println();
            System.out.println("Menu for Administrator:");
            System.out.println("1) Add new User");
            System.out.println("2) Edit User");
            System.out.println("3) Display User");
            System.out.println("0) Exit");
            System.out.print("Your choice: ");
            while (!INPUT.hasNextInt()) {
                System.out.println("Re-enter without letters. Your choice: ");
                INPUT.next();
            }
            choice = INPUT.nextInt();
            switch(choice) {
                case 1:
                    addUser();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 0:
                    menuFlag = false;
                    break;
                default:
                    System.out.println("Invalid character! Try again.");
                    break;
            }
        }
    }
}
