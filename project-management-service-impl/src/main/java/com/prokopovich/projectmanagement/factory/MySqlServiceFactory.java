package com.prokopovich.projectmanagement.factory;

import com.prokopovich.projectmanagement.service.impl.*;

public class MySqlServiceFactory extends ServiceFactory {

    @Override
    public AuthenticationServiceImpl getAuthenticationServiceImpl() {
        return new AuthenticationServiceImpl();
    }

    @Override
    public AccountActionServiceImpl getAccountActionServiceImpl() {
        return new AccountActionServiceImpl();
    }

    @Override
    public ActionServiceImpl getActionServiceImpl() {
        return new ActionServiceImpl();
    }

    @Override
    public AccountServiceImpl getAccountServiceImpl() {
        return new AccountServiceImpl();
    }

    @Override
    public UserServiceImpl getUserServiceImpl() {
        return new UserServiceImpl();
    }

}
