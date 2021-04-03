package com.prokopovich.projectmanagement.factory;

public abstract class DaoFactoryProvider implements DaoFactory {

    public static final int MYSQL = 1;

    public static DaoFactory getDAOFactory(int whichFactory) {
        switch (whichFactory) {
        case MYSQL:
            return new MySqlDaoFactory();
        default:
            return null;
        }
    }
}
