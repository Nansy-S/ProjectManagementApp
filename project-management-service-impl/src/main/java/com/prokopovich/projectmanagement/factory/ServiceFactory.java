package com.prokopovich.projectmanagement.factory;

import com.prokopovich.projectmanagement.service.impl.AccountActionServiceImpl;
import com.prokopovich.projectmanagement.service.impl.AccountServiceImpl;
import com.prokopovich.projectmanagement.service.impl.ActionServiceImpl;
import com.prokopovich.projectmanagement.service.impl.UserServiceImpl;

public abstract class ServiceFactory {

    public static final int MYSQL = 1;

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
