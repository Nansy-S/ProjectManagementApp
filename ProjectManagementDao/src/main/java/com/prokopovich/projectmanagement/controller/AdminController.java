package com.prokopovich.projectmanagement.controller;

import com.prokopovich.projectmanagement.factory.MySqlServiceFactory;
import com.prokopovich.projectmanagement.factory.ServiceFactory;
import com.prokopovich.projectmanagement.model.Account;
import com.prokopovich.projectmanagement.model.User;
import com.prokopovich.projectmanagement.service.AccountService;
import com.prokopovich.projectmanagement.service.UserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class AdminController {

    private static final Logger LOGGER = LogManager.getLogger(AdminController.class);
    private ServiceFactory service = new MySqlServiceFactory();
    private UserService userService = service.getUserServiceImpl();
    private AccountService accountService = service.getAccountServiceImpl();


    public void addUser() {
        Account newAccount = new Account(0, "Ivan", "Ivanov", "Ivanovich", "i@mail.ru", "1111", "Manager", null);
        User newUser = new User(0, "Manager", "Active", "12345", newAccount);
        Account addedAccount = accountService.addNewAccount(newAccount, newUser);
        LOGGER.debug(addedAccount.toString());
    }

    public void findAccount() {
        Account account = new Account();
        account = accountService.getByAccountId(23);
        LOGGER.debug(account.toString());
    }
}
