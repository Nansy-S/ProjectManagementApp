package com.prokopovich;

import com.prokopovich.projectmanagement.enumeration.UserRole;
import com.prokopovich.projectmanagement.factory.MySqlServiceFactory;
import com.prokopovich.projectmanagement.factory.ServiceFactory;
import com.prokopovich.projectmanagement.model.Account;
import com.prokopovich.projectmanagement.model.User;
import com.prokopovich.projectmanagement.service.AccountService;
import com.prokopovich.projectmanagement.service.UserService;
import com.prokopovich.projectmanagement.service.impl.AccountServiceImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Scanner;

public class App {

    private static final Logger LOGGER = LogManager.getLogger(App.class);
    private static final Scanner INPUT = new Scanner(System.in);

    private static Account currentUser = new Account();
    private static ServiceFactory service = new MySqlServiceFactory();
    private static UserService userService = service.getUserServiceImpl();
    private static AccountService accountService = service.getAccountServiceImpl();


    public static void main( String[] args ) {
       authorization();
    }

    private static void authorization(){
        currentUser = accountService.authorization();
        if (currentUser != null) {
            System.out.println("You entered as " + currentUser.getRole());
        }
    }

    public static void addUser() {
        System.out.println("Enter new user: ");



        Account newAccount = new Account(0, "Ivan", "Ivanov", "Ivanovich", "i@mail.ru", "1111", "Manager", null);
        User newUser = new User(0, "Manager", "Active", "12345", newAccount);
        //Account addedAccount = accountService.addNewAccount(newAccount, newUser);
        //LOGGER.debug(addedAccount.toString());
    }

    public static void findAccount() {
        Account account = new Account();
        account = accountService.getByAccountId(26);
        LOGGER.debug(account.toString());
    }
}
