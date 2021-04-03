package com.prokopovich.projectmanagement.factory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.prokopovich.projectmanagement.dao.*;
import com.prokopovich.projectmanagement.util.PropertiesUtil;
import org.apache.commons.dbcp2.BasicDataSource;

public class MySqlDaoFactory extends DaoFactoryProvider {

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

    @Override
    public AccountActionDao getAccountActionDao() {
        return new AccountActionMySqlDao();
    }

    @Override
    public AccountDao getAccountDao() {
        return new AccountMySqlDao();
    }

    @Override
    public ActionDao getActionDao() {
        return new ActionMySqlDao();
    }

    @Override
    public AttachmentDao getAttachmentDao() {
        return new AttachmentMySqlDao();
    }

    @Override
    public CommentDao getCommentDao() {
        return new CommentMySqlDao();
    }

    @Override
    public ProjectDao getProjectDao() {
        return new ProjectMySqlDao();
    }

    @Override
    public ProjectActionDao getProjectActionDao() {
        return new ProjectActionMySqlDao();
    }

    @Override
    public TaskDao getTaskDao() {
        return new TaskMySqlDao();
    }

    @Override
    public TaskActionDao getTaskActionDao() {
        return new TaskActionMySqlDao();
    }

    @Override
    public UserDao getUserDao() {
        return new UserMySqlDao();
    }
}
