package com.prokopovich.project_management.factory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.prokopovich.project_management.dao.*;
import com.prokopovich.project_management.util.PropertiesUtil;
import org.apache.commons.dbcp2.BasicDataSource;

public class MySqlDAOFactory {
    private static final String MYSQL_CONFIG_PROPERTIES = "mysql.properties";
    private static final String DRIVER_CLASS_NAME = "driverClassName";
    private static final String CONNECTION_URL = "connectionUrl";
    private static final String USER = "username";
    private static final String PASSWORD = "password";
    private static final BasicDataSource mDatasource = new BasicDataSource();

    static {
        Properties mySqlProperties = new PropertiesUtil()
                .getProperties(MYSQL_CONFIG_PROPERTIES);
        mDatasource.setDriverClassName(mySqlProperties.getProperty(DRIVER_CLASS_NAME));
        mDatasource.setUrl(mySqlProperties.getProperty(CONNECTION_URL));
        mDatasource.setUsername(mySqlProperties.getProperty(USER));
        mDatasource.setPassword(mySqlProperties.getProperty(PASSWORD));
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

    public static UserDAOImpl getUserDAO() {
        return new UserDAOImpl();
    }
}
