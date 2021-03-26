package com.prokopovich.projectmanagement.service.impl;

import com.prokopovich.App;
import com.prokopovich.projectmanagement.dao.AccountMySqlDao;
import com.prokopovich.projectmanagement.exception.DaoException;
import com.prokopovich.projectmanagement.factory.DaoFactoryProvider;
import com.prokopovich.projectmanagement.model.Account;
import com.prokopovich.projectmanagement.service.AuthenticationService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class AuthenticationServiceImpl implements AuthenticationService {

    private static final Logger LOGGER = LogManager.getLogger(App.class);
    private static final AccountMySqlDao ACCOUNT_DAO = (AccountMySqlDao) DaoFactoryProvider.getDAOFactory(1).getAccountDao();

    @Override
    public Account userAuthorization(String login, String password) {
        try {
            Account account = ACCOUNT_DAO.findByEmail(login);
            if (account.getEmail() != null) {
                if (account.getPassword().equals(password)) {
                    LOGGER.debug("User entered as " + account.getRole());
                    return account;
                } else {
                    LOGGER.debug("Authorization unsuccessful. Invalid password.");
                    return null;
                }
            } else {
                LOGGER.debug("Authorization unsuccessful. User with this email does not exist.");
                return null;
            }
        } catch (Exception ex) {
            throw new DaoException(ex);
        }
    }
}
