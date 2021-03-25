package com.prokopovich;

import com.prokopovich.projectmanagement.controller.AccountController;
import com.prokopovich.projectmanagement.enumeration.UserRole;
import com.prokopovich.projectmanagement.factory.MySqlServiceFactory;
import com.prokopovich.projectmanagement.factory.ServiceFactory;
import com.prokopovich.projectmanagement.model.Account;
import com.prokopovich.projectmanagement.service.AuthenticationService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Scanner;

public class App {

    private static final Logger LOGGER = LogManager.getLogger(App.class);
    private static final Scanner INPUT = new Scanner(System.in);
    private static final AccountController ACCOUNT_CONTROLLER = new AccountController();

    private static Account currentUser = new Account();
    private static UserRole currentUserRole;
    private static ServiceFactory service = new MySqlServiceFactory();
    private static AuthenticationService authenticationService = service.getAuthenticationServiceImpl();

    public static void main( String[] args ) {
        LOGGER.debug("Application is running.");
        authorization();
    }

    public static Account getCurrentUser(){
        return currentUser;
    }

    private static void authorization() {
        LOGGER.trace("Authorization is running.");
        System.out.print("Enter your email: ");
        String login = INPUT.nextLine();
        System.out.print("Enter your password: ");
        String password = INPUT.nextLine();
        currentUser = authenticationService.userAuthorization(login, password);
        if (currentUser != null) {
            LOGGER.debug("Current user - " + currentUser.toString());
            currentUserRole = UserRole.fromString(currentUser.getRole());
            switch (currentUserRole) {
                case ADMIN:
                    menuAdmin();
                    break;
            }
        }
    }

    public static void menuAdmin() {
        int choice;
        boolean menuFlag = true;

        LOGGER.trace("Menu for Administrator shown.");
        while (menuFlag) {
            System.out.println("\nMenu for Administrator:");
            System.out.println("1) Display User");
            System.out.println("2) Add new User");
            System.out.println("3) Edit User");
            System.out.println("4) Change User role");
            System.out.println("5) Change User status");
            System.out.println("0) Exit");
            System.out.print("Your choice: ");
            while (!INPUT.hasNextInt()) {
                System.out.println("Re-enter without letters. Your choice: ");
                INPUT.next();
            }
            choice = INPUT.nextInt();
            switch(choice) {
                case 1:
                    ACCOUNT_CONTROLLER.displayUsersByReporter();
                    break;
                case 2:
                    ACCOUNT_CONTROLLER.addUser();
                    break;
                case 3:
                    ACCOUNT_CONTROLLER.editUser();
                    break;
                case 4:
                    ACCOUNT_CONTROLLER.changeUserRole();
                    break;
                case 5:
                    ACCOUNT_CONTROLLER.changeUserStatus();
                    break;
                case 0:
                    LOGGER.trace("Application execution completed.");
                    menuFlag = false;
                    break;
                default:
                    System.out.println("Invalid character! Try again.");
                    break;
            }
        }
    }
}
