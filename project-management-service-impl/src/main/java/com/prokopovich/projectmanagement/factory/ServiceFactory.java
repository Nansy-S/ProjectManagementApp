package com.prokopovich.projectmanagement.factory;

import com.prokopovich.projectmanagement.service.impl.*;

public abstract class ServiceFactory {

    public static final int MYSQL = 1;

    public abstract AuthenticationServiceImpl getAuthenticationServiceImpl();

    public abstract AccountActionServiceImpl getAccountActionServiceImpl();

    public abstract ActionServiceImpl getActionServiceImpl();

    public abstract AccountServiceImpl getAccountServiceImpl();

    public abstract UserServiceImpl getUserServiceImpl();


    public static ServiceFactory getServiceFactory(int whichFactory) {
        switch (whichFactory) {
        case MYSQL:
            return new MySqlServiceFactory();
        default:
            return null;
        }
    }
}
