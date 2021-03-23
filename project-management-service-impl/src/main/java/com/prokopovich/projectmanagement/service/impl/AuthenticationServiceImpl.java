package com.prokopovich.projectmanagement.service.impl;

import com.prokopovich.projectmanagement.dao.AccountMySqlDao;
import com.prokopovich.projectmanagement.exception.DaoException;
import com.prokopovich.projectmanagement.factory.DaoFactory;
import com.prokopovich.projectmanagement.model.Account;
import com.prokopovich.projectmanagement.service.AuthenticationService;

public class AuthenticationServiceImpl implements AuthenticationService {

    private static final AccountMySqlDao ACCOUNT_DAO = (AccountMySqlDao) DaoFactory.getDAOFactory(1).getAccountDao();

    @Override
    public Account userAuthorization(String login, String password) {
        try {
            Account account = ACCOUNT_DAO.findAllByEmail(login);
            if (account != null) {
                if (account.getPassword().equals(password)) {
                    System.out.println("Authorization was successful!");
                    return account;
                } else {
                    System.out.println("Invalid password.");
                    return null;
                }
            } else {
                System.out.println("Invalid email.");
                return null;
            }
        } catch (Exception ex) {
            throw new DaoException(ex);
        }
    }
}
