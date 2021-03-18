package com.prokopovich;

import com.prokopovich.projectmanagement.factory.MySqlServiceFactory;
import com.prokopovich.projectmanagement.factory.ServiceFactory;
import com.prokopovich.projectmanagement.model.Account;
import com.prokopovich.projectmanagement.model.User;
import com.prokopovich.projectmanagement.service.AccountService;
import com.prokopovich.projectmanagement.service.UserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class App {

    private static final Logger LOGGER = LogManager.getLogger(App.class);
    private static ServiceFactory service = new MySqlServiceFactory();
    private static UserService userService = service.getUserServiceImpl();
    private static AccountService accountService = service.getAccountServiceImpl();

    public static void main( String[] args ) {

        findAccount();

    }

    public static void addUser() {
        Account newAccount = new Account(0, "Ivan", "Ivanov", "Ivanovich", "i@mail.ru", "1111", "Manager", null);
        User newUser = new User(0, "Manager", "Active", "12345", newAccount);
        Account addedAccount = accountService.addNewAccount(newAccount, newUser);
        LOGGER.debug(addedAccount.toString());
    }

    public static void findAccount() {
        Account account = new Account();
        account = accountService.getByAccountId(26);
        LOGGER.debug(account.toString());
    }
}
