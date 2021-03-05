package com.prokopovich.project_management.factory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.prokopovich.project_management.dao.*;
import org.apache.commons.dbcp.BasicDataSource;

public class MySQLDAOFactory extends DAOFactory {
    private static final String MYSQL_CONFIG_PROPERTIES = "mysql.properties";
    private static final String DRIVER_CLASS_NAME = "driverClassName";
    private static final String CONNECTION_URL = "connectionUrl";
    private static final String USER = "username";
    private static final String PASSWORD = "password";
    private static BasicDataSource mDatasource;

    public MySQLDAOFactory() {
        ResourceBundle resource = ResourceBundle.getBundle(MYSQL_CONFIG_PROPERTIES);
        mDatasource = new BasicDataSource();
        mDatasource.setDriverClassName(resource.getString(DRIVER_CLASS_NAME));
        mDatasource.setUrl(resource.getString(CONNECTION_URL));
        mDatasource.setUsername(resource.getString(USER));
        mDatasource.setPassword(resource.getString(PASSWORD));
    }

    public static Connection getConnection() throws SQLException {
        return mDatasource.getConnection();
    }


    public AccountActionDAO getAccountActionDAO() {
        return null;
    }

    public AccountDAO getAccountDAO() {
        return null;
    }

    public ActionDAO getActionDAO() {
        return null;
    }

    public AttachmentDAO getAttachmentDAO() {
        return null;
    }

    public CommentDAO getCommentDAO() {
        return null;
    }

    public ProjectDAO getProjectDAO() {
        return null;
    }

    public TaskDAO getTaskDAO() {
        return null;
    }

    public TeamDAO getTeamDAO() {
        return null;
    }

    public UserDAO getUserDAO() {
        return null;
    }
}
