package com.prokopovich.project_management.dao;

import com.prokopovich.project_management.factory.MySQLDAOFactory;
import com.prokopovich.project_management.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class UserMySQLDAO implements UserDAO {
    private static final String SQL = "SELECT user_id, position, team_id, current_status, phone FROM users";

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
        try {
            List<User> users = new ArrayList<User>();
            User userBean;
            Connection connection = MySQLDAOFactory.getConnection();
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
            }
            return users;
        } catch (SQLException ex) {
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
