package com.prokopovich.projectmanagement.dao.mysql;

import com.prokopovich.projectmanagement.exception.DaoException;
import com.prokopovich.projectmanagement.factory.MySqlDaoFactory;
import com.prokopovich.projectmanagement.dao.UserDao;
import com.prokopovich.projectmanagement.model.Account;
import com.prokopovich.projectmanagement.model.User;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserMySqlDao extends GenericMySqlDao<User> implements UserDao {
    private static final String SQL_SELECT_ALL = "SELECT * FROM users";
    private static final String SQL_SELECT_ONE = "SELECT * FROM users WHERE user_id = ?";
    private static final String SQL_SELECT_BY_STATUS = "SELECT * FROM users WHERE current_status = ?";
    private static final String SQL_CREATE = "INSERT INTO users (user_id, position, current_status, phone) VALUES (?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE users SET user_id = ?, position = ?, current_status = ?, phone = ? WHERE user_id = ?";
    private static final Logger LOGGER = LogManager.getLogger(UserMySqlDao.class);

    public UserMySqlDao(){
        super(new User(), new ArrayList<User>());
    }

    @Override
    public String getSqlSelectAll() {
        return SQL_SELECT_ALL;
    }

    @Override
    public String getSqlSelectOne() {
        return SQL_SELECT_ONE;
    }

    @Override
    public String getSqlCreate() {
        return SQL_CREATE;
    }

    @Override
    public String getSqlUpdate() {
        return SQL_UPDATE;
    }

    @Override
    protected User getStatement(ResultSet rs) throws SQLException {
        User userBean = new User();
        userBean.setUserId(rs.getInt(1));
        userBean.setPosition(rs.getString(2));
        userBean.setCurrentStatus(rs.getString(3));
        userBean.setPhone(rs.getString(4));
        return userBean;
    }

    @Override
    public void setStatement(User user, PreparedStatement statement) throws SQLException {
        statement.setInt(1, user.getUserId());
        statement.setString(2, user.getPosition());
        statement.setString(3, user.getCurrentStatus());
        statement.setString(4, user.getPhone());
    }

    @Override
    public void setStatementUpdate(User user, PreparedStatement statement) throws SQLException {
        setStatement(user, statement);
        statement.setInt(5, user.getUserId());
    }

    @Override
    public Collection<User> findAllByCurrentStatus(String currentStatus) throws DaoException {
        LOGGER.trace("findAllByCurrentStatus method is executed - currentStatus = " + currentStatus);
        List<User> users = new ArrayList<User>();
        User user;
        try (Connection connection = MySqlDaoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_STATUS)) {
            statement.setString(1, currentStatus);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                user = getStatement(rs);
                LOGGER.debug(user.toString());
                users.add(user);
            }
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
        return users;
    }
}
