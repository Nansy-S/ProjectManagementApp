package com.prokopovich.projectmanagement.factory;

public abstract class ServiceFactoryProvider implements ServiceFactory {

    public static final int MYSQL = 1;

    public static ServiceFactory getServiceFactory(int whichFactory) {
        switch (whichFactory) {
        case MYSQL:
            return new ServiceFactoryImpl();
        default:
            return null;
        }
    }
}
