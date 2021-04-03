package com.prokopovich.projectmanagement.factory;

import com.prokopovich.projectmanagement.enumeration.DatabaseType;

public abstract class DaoFactoryProvider implements DaoFactory {

    public static DaoFactory getDAOFactory(DatabaseType whichFactory) {
        switch (whichFactory) {
        case MYSQL:
            return new MySqlDaoFactory();
        default:
            return null;
        }
    }
}
