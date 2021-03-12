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
    private static final String SQL_CREATE = "INSERT INTO users (user_id, position, current_status, phone) VALUES (?, ?, ?, ?)";
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
    public User create(User newUser){
        try (Connection connection = MySqlDaoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_CREATE)) {
            statement.setInt(1, newUser.getUserId());
            statement.setString(2, newUser.getPosition());
            statement.setString(3, newUser.getCurrentStatus());
            statement.setString(4, newUser.getPhone());
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
        return newUser;
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
                logger.debug("User.userId:" + userBean.getUserId() +
                        " User.position:" + userBean.getPosition() +
                        " User.currentStatus:" + userBean.getCurrentStatus() +
                        " User.phone:" + userBean.getPhone());
            }
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }
        return users;
    }

    public boolean updateUser(int userId) {
        return false;
    }

    public Collection<User> findAllByCurrentStatus(String currentStatus) {
        return null;
    }
}
