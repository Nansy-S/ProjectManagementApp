package com.prokopovich.projectmanagement.factory;

import com.prokopovich.projectmanagement.service.impl.AccountActionServiceImpl;
import com.prokopovich.projectmanagement.service.impl.AccountServiceImpl;
import com.prokopovich.projectmanagement.service.impl.ActionServiceImpl;
import com.prokopovich.projectmanagement.service.impl.UserServiceImpl;

public class MySqlServiceFactory extends ServiceFactory {

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
