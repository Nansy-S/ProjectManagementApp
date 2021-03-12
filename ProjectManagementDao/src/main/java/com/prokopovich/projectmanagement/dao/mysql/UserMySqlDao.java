package com.prokopovich.projectmanagement.dao.mysql;

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

public class UserMySqlDao implements UserDao {
    private static final String SQL_FIND_ALL = "SELECT * FROM users";
    static Logger logger = LogManager.getLogger(UserMySqlDao.class);

    public int createUser(User user) {
        return 0;
    }

    public boolean updateUser(int userId) {
        return false;
    }

    @Override
    public User findUser(int userId) {
        return null;
    }

    @Override
    public Collection<User> findAllUsers() {
        try (Connection connection = MySqlDaoFactory.getConnection()) {
            List<User> users = new ArrayList<User>();
            User userBean;
            PreparedStatement stm = connection.prepareStatement(SQL_FIND_ALL);
            ResultSet rs = stm.executeQuery();
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
            return users;
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
            return Collections.emptyList();
        }
    }

    public Collection<User> findAllByCurrentStatus(String currentStatus) {
        return null;
    }

    public Collection<User> findAllByTeamId(int teamId) {
        return null;
    }
}
