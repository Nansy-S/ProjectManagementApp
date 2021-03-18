package com.prokopovich.projectmanagement.dao.mysql;

import com.prokopovich.projectmanagement.dao.AccountDao;
import com.prokopovich.projectmanagement.dao.ActionDao;
import com.prokopovich.projectmanagement.dao.UserDao;
import com.prokopovich.projectmanagement.exception.DaoException;
import com.prokopovich.projectmanagement.factory.MySqlDaoFactory;
import com.prokopovich.projectmanagement.model.Account;
import com.prokopovich.projectmanagement.model.Action;
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
import java.util.Set;

public class AccountMySqlDao extends GenericMySqlDao<Account> implements AccountDao {

    private static final String SQL_SELECT_ALL = "SELECT account_id, name, surname, patronymic, email, password, " +
            "role, photo FROM accounts";
    private static final String SQL_SELECT_ONE = "SELECT account_id, name, surname, patronymic, email, password, " +
            "role, photo FROM accounts WHERE account_id = ?";
    private static final String SQL_SELECT_BY_ROLE = "SELECT account_id, name, surname, patronymic, email, password, " +
            "role, photo FROM accounts WHERE current_status = ?";
    private static final String SQL_SELECT_BY_FULL_NAME = "SELECT account_id, name, surname, patronymic, email, " +
            "password, role, photo FROM accounts WHERE CONCAT(surname, ' ', name, ' ', patronymic) LIKE '%?%'";
    private static final String SQL_SELECT_BY_EMAIL_AND_PASSWORD = "SELECT account_id, name, surname, patronymic, " +
            "email, password, role, photo FROM accounts WHERE email = ? AND password = ?";
    private static final String SQL_CREATE = "INSERT INTO accounts " +
            "(name, surname, patronymic, email, password, role , photo) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_LAST_INSERT = "SELECT account_id, name, surname, patronymic, email, password, " +
            "role, photo FROM accounts WHERE account_id = last_insert_id()";
    private static final String SQL_UPDATE = "UPDATE accounts SET name = ?, surname = ?, patronymic = ?, email = ?, " +
            "password = ?, role = ?, photo = ? WHERE account_id = ?";
    private static final Logger LOGGER = LogManager.getLogger(AccountMySqlDao.class);

    public AccountMySqlDao(){
        super(new Account(), new ArrayList<Account>());
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
        //ActionDao actionDao = new ActionMySqlDao();
        //Set<Action> actions = (Set<Action>) actionDao.findAllByReporter(rs.getInt(1));
        //account.setActions(actions);
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
        LOGGER.trace("Update account method is executed");
        try (Connection connection = MySqlDaoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_UPDATE)) {
            setStatement(account, statement);
            statement.setInt(8, account.getAccountId());
            statement.executeUpdate();
            LOGGER.debug("Updated account: " + account.toString());
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
        return true;
    }

    @Override
    public Collection<Account> findAllByUserRole(String role) throws DaoException {
        LOGGER.trace("findAllByUserRole method is executed - role = " + role);
        List<Account> accounts = (List<Account>) findByParameter(SQL_SELECT_BY_ROLE, role);
        return accounts;
    }

    @Override
    public Collection<Account> findAllByUserFullName(String fullName) throws DaoException {
        LOGGER.trace("findAllByUserFullName method is executed - Full name = " + fullName);
        List<Account> accounts = (List<Account>) findByParameter(SQL_SELECT_BY_FULL_NAME, fullName);
        return accounts;
    }

    @Override
    public Collection<Account> findAllByEmailAndPassword(String email, String password) throws DaoException {
        LOGGER.trace("findAllByEmailAndPassword method is executed - email = " + email);
        List<Account> accountList = new ArrayList<>();
        Account account = new Account();
        try (Connection connection = MySqlDaoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_EMAIL_AND_PASSWORD)) {
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                account = getStatement(rs);
                LOGGER.debug(account.toString());
            }
            accountList.add(account);
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
        return accountList;
    }
}
