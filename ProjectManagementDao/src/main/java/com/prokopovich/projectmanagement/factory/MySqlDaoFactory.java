package com.prokopovich.projectmanagement.factory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.prokopovich.projectmanagement.dao.*;
import com.prokopovich.projectmanagement.dao.mysql.UserMySqlDao;
import com.prokopovich.projectmanagement.util.PropertiesUtil;
import org.apache.commons.dbcp2.BasicDataSource;

public class MySqlDaoFactory extends DaoFactory {
    private static final String MYSQL_CONFIG_PROPERTIES = "mysql.properties";
    private static final String DRIVER_CLASS_NAME = "driverClassName";
    private static final String CONNECTION_URL = "connectionUrl";
    private static final String USER = "username";
    private static final String PASSWORD = "password";
    private static BasicDataSource dataSource = new BasicDataSource();

    static {
        Properties mySqlProperties = new PropertiesUtil()
                .getProperties(MYSQL_CONFIG_PROPERTIES);
        dataSource.setDriverClassName(mySqlProperties.getProperty(DRIVER_CLASS_NAME));
        dataSource.setUrl(mySqlProperties.getProperty(CONNECTION_URL));
        dataSource.setUsername(mySqlProperties.getProperty(USER));
        dataSource.setPassword(mySqlProperties.getProperty(PASSWORD));
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public AccountActionDao getAccountActionDAO() {
        return null;
    }

    public AccountDao getAccountDAO() {
        return null;
    }

    public ActionDao getActionDAO() {
        return null;
    }

    public AttachmentDao getAttachmentDAO() {
        return null;
    }

    public CommentDao getCommentDAO() {
        return null;
    }

    public ProjectDao getProjectDAO() {
        return null;
    }

    public TaskDao getTaskDAO() {
        return null;
    }

    public UserDao getUserDAO() {
        return new UserMySqlDao();
    }
}
