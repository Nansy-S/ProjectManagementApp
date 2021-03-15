package com.prokopovich.projectmanagement.factory;

import com.prokopovich.projectmanagement.service.impl.AccountServiceImpl;

public abstract class ServiceFactory {

    public static final int MYSQL = 1;

    public abstract AccountServiceImpl getAccountServiceImpl();

    public static ServiceFactory getServiceFactory(int whichFactory) {

        switch (whichFactory) {
            case MYSQL:
                return new MySqlServiceFactory();
            default:
                return null;
        }
    }
}
