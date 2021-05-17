package com.prokopovich.projectmanagement.service.impl;

import com.prokopovich.App;
import com.prokopovich.projectmanagement.dao.AccountDao;
import com.prokopovich.projectmanagement.exception.DaoException;
import com.prokopovich.projectmanagement.model.Account;
import com.prokopovich.projectmanagement.service.AuthorizationService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class AuthorizationServiceImpl implements AuthorizationService {

    private static final Logger LOGGER = LogManager.getLogger(App.class);

    private final AccountDao accountDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthorizationServiceImpl(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public Account userAuthorization(String login, String password) {
        try {
            Account account = accountDao.findByEmail(login);
            if (account.getEmail() != null) {
                if (passwordEncoder.matches(password, account.getPassword())) {
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
