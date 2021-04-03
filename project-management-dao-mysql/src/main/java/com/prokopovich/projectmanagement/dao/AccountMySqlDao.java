package com.prokopovich.projectmanagement.dao;

import com.prokopovich.projectmanagement.enumeration.DatabaseType;
import com.prokopovich.projectmanagement.exception.DaoException;
import com.prokopovich.projectmanagement.factory.MySqlDaoFactory;
import com.prokopovich.projectmanagement.model.Account;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.Collection;
import java.util.List;

public class AccountMySqlDao extends GenericMySqlDaoWithHistory<Account> implements AccountDao {

    private static final String SQL_SELECT_ALL = "SELECT account_id, name, surname, patronymic, email, password, " +
            "role, photo FROM accounts";
    private static final String SQL_SELECT_ONE = "SELECT account_id, name, surname, patronymic, email, password, " +
            "role, photo FROM accounts WHERE account_id = ?";
    private static final String SQL_SELECT_BY_ROLE = "SELECT account_id, name, surname, patronymic, email, password, " +
            "role, photo FROM accounts WHERE current_status = ?";
    private static final String SQL_SELECT_BY_FULL_NAME = "SELECT account_id, name, surname, patronymic, email, " +
            "password, role, photo FROM accounts WHERE CONCAT(surname, ' ', name, ' ', patronymic) LIKE '%?%'";
    private static final String SQL_SELECT_BY_EMAIL = "SELECT account_id, name, surname, patronymic, " +
            "email, password, role, photo FROM accounts WHERE email = ?";
    private static final String SQL_SELECT_BY_REPORTER_AND_ACTION = "SELECT account_id, name, surname, patronymic, " +
            "email, password, role, photo FROM accounts acc WHERE acc.account_id IN " +
            "(SELECT aa.account_id FROM account_actions aa INNER JOIN actions a ON a.action_id = aa.action_id " +
            "WHERE a.reporter = ? AND a.type = ?)";
    private static final String SQL_CREATE = "INSERT INTO accounts " +
            "(name, surname, patronymic, email, password, role , photo) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE accounts SET name = ?, surname = ?, patronymic = ?, email = ?, " +
            "password = ?, role = ?, photo = ? WHERE `account_id` = ?";

    private static final Logger LOGGER = LogManager.getLogger(AccountMySqlDao.class);

    private final AccountActionDao accountActionDao = new AccountActionMySqlDao();

    public AccountMySqlDao() {
        super();
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
    public String getSqlLastInsert() {
        return SQL_CREATE;
    }

    @Override
    public String getSqlSelectByReporterAndAction() {
        return SQL_SELECT_BY_REPORTER_AND_ACTION;
    }

    @Override
    protected Account getStatement(ResultSet rs) throws SQLException {
        Account account = new Account();

        account.setAccountId(rs.getInt(1));
        account.setName(rs.getString(2));
        account.setSurname(rs.getString(3));
        account.setPatronymic(rs.getString(4));
        account.setEmail(rs.getString(5));
        account.setPassword(rs.getString(6));
        account.setRole(rs.getString(7));
        //account.setPhoto(rs.getBlob(8));
        return account;
    }

    @Override
    protected void setStatement(Account account, PreparedStatement statement) throws SQLException {
        statement.setString(1, account.getName());
        statement.setString(2, account.getSurname());
        statement.setString(3, account.getPatronymic());
        statement.setString(4, account.getEmail());
        statement.setString(5, account.getPassword());
        statement.setString(6, account.getRole());
        statement.setBlob(7, account.getPhoto());
    }

    @Override
    public boolean update(Account account) throws DaoException {
        LOGGER.trace("update account method is executed");
        try (Connection connection = MySqlDaoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_UPDATE)) {
            setStatement(account, statement);
            statement.setInt(8, account.getAccountId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
        return true;
    }

    @Override
    public Collection<Account> findAllByUserRole(String role) throws DaoException {
        LOGGER.trace("findAllByUserRole method is executed - role = " + role);
        return findByParameter(SQL_SELECT_BY_ROLE, role);
    }

    @Override
    public Collection<Account> findAllByUserFullName(String fullName) throws DaoException {
        LOGGER.trace("findAllByUserFullName method is executed - Full name = " + fullName);
        return findByParameter(SQL_SELECT_BY_FULL_NAME, fullName);
    }

    @Override
    public Account findByEmail(String email) throws DaoException {
        LOGGER.trace("findAllByEmail method is executed - email = " + email);
        List<Account> accounts = (List<Account>) findByParameter(SQL_SELECT_BY_EMAIL, email);
        return accounts.iterator().next();
    }
}
