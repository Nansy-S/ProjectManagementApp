package com.prokopovich.projectmanagement.factory;

import com.prokopovich.projectmanagement.enumeration.DatabaseType;

public abstract class ServiceFactoryProvider implements ServiceFactory {

    public static ServiceFactory getServiceFactory(DatabaseType whichFactory) {
        switch (whichFactory) {
        case MYSQL:
            return new ServiceFactoryImpl();
        default:
            return null;
        }
    }
}
