package com.prokopovich.projectmanagement.service.impl;

import com.prokopovich.projectmanagement.dao.AccountMySqlDao;
import com.prokopovich.projectmanagement.factory.DaoFactory;
import com.prokopovich.projectmanagement.factory.ServiceFactory;
import com.prokopovich.projectmanagement.model.Account;
import com.prokopovich.projectmanagement.model.AccountAction;
import com.prokopovich.projectmanagement.model.Action;
import com.prokopovich.projectmanagement.model.User;
import com.prokopovich.projectmanagement.service.AccountActionService;
import com.prokopovich.projectmanagement.service.AccountService;
import com.prokopovich.projectmanagement.service.ActionService;
import com.prokopovich.projectmanagement.service.UserService;

import java.util.List;
import java.util.Scanner;

public class AccountServiceImpl implements AccountService {

    private static final Scanner INPUT = new Scanner(System.in);
    private static final AccountMySqlDao ACCOUNT_DAO = (AccountMySqlDao) DaoFactory.getDAOFactory(1).getAccountDao();
    private static final UserService USER_SERVICE = ServiceFactory.getServiceFactory(1).getUserServiceImpl();
    private static final ActionService ACTION_SERVICE = ServiceFactory.getServiceFactory(1).getActionServiceImpl();
    private static final AccountActionService ACCOUNT_ACTION_SERVICE =
            ServiceFactory.getServiceFactory(1).getAccountActionServiceImpl();

    @Override
    public Account authorization() {
        System.out.println("Enter your email: ");
        String login = INPUT.nextLine();
        System.out.println("Enter your password: ");
        String password = INPUT.nextLine();
        Account account = (Account) ACCOUNT_DAO.findAllByEmail(login);
        if (account != null) {
            if (account.getPassword().equals(password)) {
                System.out.println("Authorization was successful!");
                return account;
            }
            else {
                System.out.println("Invalid password.");
                return null;
            }
        }
        else {
            System.out.println("Invalid email.");
            return null;
        }
    }

    @Override
    public Account addNewAccount(Account newAccount, User newUser, Action newAction, AccountAction newAccountAction) {
        newAccount = ACCOUNT_DAO.create(newAccount);
        newUser.setUserId(newAccount.getAccountId());
        USER_SERVICE.addNewUser(newUser);
        newAction = ACTION_SERVICE.addNewAction(newAction);
        newAccountAction.setActionId(newAction.getActionId());
        newAccountAction.setAccountId(newAccount.getAccountId());
        newAccountAction.setAction(newAction);
        ACCOUNT_ACTION_SERVICE.addNewAccountAction(newAccountAction);
        return newAccount;
    }

    @Override
    public void editAccount(Account account) {
        ACCOUNT_DAO.update(account);
    }

    @Override
    public Account getByAccountId(int id) {
        return ACCOUNT_DAO.findOne(id);
    }

    @Override
    public List<Account> getAll() {
        return (List<Account>) ACCOUNT_DAO.findAll();
    }

    @Override
    public List<Account> getAllByUserRole(String role) {
        return (List<Account>) ACCOUNT_DAO.findAllByUserRole(role);
    }

    @Override
    public List<Account> getAllByUserFullName(String fullName) {
        return (List<Account>) ACCOUNT_DAO.findAllByUserFullName(fullName);
    }
}
