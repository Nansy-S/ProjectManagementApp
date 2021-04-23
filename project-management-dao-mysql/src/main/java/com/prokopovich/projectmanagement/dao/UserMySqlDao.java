package com.prokopovich.projectmanagement.dao;

import com.prokopovich.projectmanagement.exception.DaoException;
import com.prokopovich.projectmanagement.factory.MySqlDaoFactory;
import com.prokopovich.projectmanagement.model.AccountAction;
import com.prokopovich.projectmanagement.model.User;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public class UserMySqlDao extends GenericMySqlDao<User> implements UserDao {

    private static final String SQL_SELECT_ALL = "SELECT user_id, position, current_status, phone FROM users";
    private static final String SQL_SELECT_ONE = "SELECT user_id, position, current_status, phone FROM users " +
            "WHERE user_id = ?";
    private static final String SQL_SELECT_BY_STATUS = "SELECT user_id, position, current_status, phone FROM users " +
            "WHERE current_status = ?";
    private static final String SQL_CREATE = "INSERT INTO users (user_id, position, current_status, phone) " +
            "VALUES (?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE users SET user_id = ?, position = ?, current_status = ?, " +
            "phone = ? WHERE user_id = ?";
    private static final String SQL_LAST_INSERT = "SELECT user_id, position, current_status, phone FROM users " +
            "WHERE user_id = last_insert_id()";
    private static final Logger LOGGER = LogManager.getLogger(UserMySqlDao.class);

    private final AccountDao accountDao;
    private final AccountActionDao accountActionDao;

    public UserMySqlDao(AccountDao accountDao, AccountActionDao accountActionDao) {
        super();
        this.accountDao = accountDao;
        this.accountActionDao = accountActionDao;
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
    protected User getStatement(ResultSet rs) throws SQLException {
        User user = new User();

        user.setUserId(rs.getInt(1));
        user.setPosition(rs.getString(2));
        user.setCurrentStatus(rs.getString(3));
        user.setPhone(rs.getString(4));
        user.setAccountInfo(accountDao.findOne(user.getUserId()));
        user.setAccountActions((List<AccountAction>) accountActionDao.findAllByAccountId(user.getUserId()));
        return user;
    }

    @Override
    public void setStatement(User user, PreparedStatement statement) throws SQLException {
        statement.setInt(1, user.getUserId());
        statement.setString(2, user.getPosition());
        statement.setString(3, user.getCurrentStatus());
        statement.setString(4, user.getPhone());
    }

    @Override
    public boolean update(User user) throws DaoException {
        LOGGER.trace("update user method is executed");
        try (Connection connection = MySqlDaoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_UPDATE)) {
            setStatement(user, statement);
            statement.setInt(5, user.getUserId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
        return true;
    }

    @Override
    public Collection<User> findAllByCurrentStatus(String currentStatus) throws DaoException {
        LOGGER.trace("findAllByCurrentStatus method is executed - currentStatus = " + currentStatus);
        return findByParameter(SQL_SELECT_BY_STATUS, currentStatus);
    }
}
