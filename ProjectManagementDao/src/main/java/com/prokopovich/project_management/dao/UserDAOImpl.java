package com.prokopovich.project_management.dao;

import com.prokopovich.project_management.factory.MySqlDAOFactory;
import com.prokopovich.project_management.model.User;
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

public class UserDAOImpl implements UserDAO {
    private static final String SQL = "SELECT user_id, position, team_id, current_status, phone FROM users";
    static Logger logger = LogManager.getLogger(UserDAOImpl.class);

    public int createUser(User user) {
        return 0;
    }

    public boolean updateUser(int userId) {
        return false;
    }

    public User findUser(int userId) {
        return null;
    }

    public Collection<User> findAll() {
        try(Connection connection = MySqlDAOFactory.getConnection()){
            List<User> users = new ArrayList<User>();
            User userBean;
            PreparedStatement ptmt = connection.prepareStatement(SQL);
            ResultSet rs = ptmt.executeQuery();
            while (rs.next()) {
                userBean = new User();
                userBean.setUserId(rs.getInt(1));
                userBean.setPosition(rs.getString(2));
                userBean.setTeamId(rs.getInt(3));
                userBean.setCurrentStatus(rs.getString(4));
                userBean.setPhone(rs.getString(5));
                users.add(userBean);
                logger.debug("User.userId:" + userBean.getUserId() +
                        " User.position:" + userBean.getPosition() +
                        " User.teamId:" + userBean.getTeamId() +
                        " User.currentStatus:" + userBean.getCurrentStatus()+
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
