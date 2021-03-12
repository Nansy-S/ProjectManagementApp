package com.prokopovich.projectmanagement.dao.mysql;

import com.prokopovich.projectmanagement.exception.DaoException;
import com.prokopovich.projectmanagement.factory.MySqlDaoFactory;
import com.prokopovich.projectmanagement.dao.UserDao;
import com.prokopovich.projectmanagement.model.User;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class UserMySqlDao extends GenericMySqlDao<User> implements UserDao {
    private static final String SQL_SELECT_ALL = "SELECT * FROM users";
    private static final String SQL_SELECT_ONE = "SELECT * FROM users WHERE user_id = ?";
    private static final String SQL_SELECT_BY_STATUS = "SELECT * FROM users WHERE current_status = ?";
    private static final String SQL_CREATE = "INSERT INTO users (user_id, position, current_status, phone) VALUES (?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE users SET user_id = ?, position = ?, current_status = ?, phone = ? WHERE user_id = ?";
    private static Logger logger = LogManager.getLogger(UserMySqlDao.class);

    @Override
    public String getSqlSelectAll() {
        return SQL_SELECT_ALL;
    }

    @Override
    public String getSqlSelectOne() {
        return SQL_SELECT_ONE;
    }

    @Override
    protected List<User> parseResultSet(ResultSet rs) {
        List<User> users = new ArrayList<User>();
        User userBean;
        try {
            while (rs.next()) {
                userBean = new User();
                userBean.setUserId(rs.getInt(1));
                userBean.setPosition(rs.getString(2));
                userBean.setCurrentStatus(rs.getString(3));
                userBean.setPhone(rs.getString(4));
                users.add(userBean);
                logger.debug(userBean.toString());
            }
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
            throw new DaoException(ex);
        }
        return users;
    }

    @Override
    public User create(User newUser) {
        logger.debug("create user method is executed");
        try (Connection connection = MySqlDaoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_CREATE)) {
            setStatement(newUser, statement);
            statement.executeUpdate();
            logger.debug("New added user: " + newUser.toString());
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
            throw new DaoException(ex);
        }
        return newUser;
    }

    @Override
    public boolean updateUser(User user) {
        logger.debug("updateUser method is executed");
        try (Connection connection = MySqlDaoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_UPDATE)) {
            setStatement(user, statement);
            statement.setInt(5, user.getUserId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
            throw new DaoException(ex);
        }
        return true;
    }

    @Override
    public Collection<User> findAllByCurrentStatus(String currentStatus) {
        logger.debug("findAllByCurrentStatus method is executed");
        List<User> users;
        try (Connection connection = MySqlDaoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_STATUS)) {
            statement.setString(1, currentStatus);
            ResultSet rs = statement.executeQuery();
            users = parseResultSet(rs);
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
            throw new DaoException(ex);
        }
        return users;
    }

    public void setStatement(User user, PreparedStatement statement) throws DaoException {
        try {
            statement.setInt(1, user.getUserId());
            statement.setString(2, user.getPosition());
            statement.setString(3, user.getCurrentStatus());
            statement.setString(4, user.getPhone());
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
            throw new DaoException(ex);
        }
    }
}
