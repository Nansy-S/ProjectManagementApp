package com.prokopovich.projectmanagement.factory;

import com.prokopovich.projectmanagement.service.impl.AccountServiceImpl;

public class MySqlServiceFactory extends ServiceFactory{

    @Override
    public AccountServiceImpl getAccountServiceImpl() {
        return new AccountServiceImpl();
    }
}
