package com.prokopovich.projectmanagement.dao;

import com.prokopovich.projectmanagement.exception.DaoException;
import com.prokopovich.projectmanagement.factory.DaoFactoryProvider;
import com.prokopovich.projectmanagement.factory.MySqlDaoFactory;
import com.prokopovich.projectmanagement.model.Account;
import com.prokopovich.projectmanagement.model.AccountAction;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AccountMySqlDao extends GenericMySqlDao<Account> implements AccountDao {

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
    private static final String SQL_CREATE = "INSERT INTO accounts " +
            "(name, surname, patronymic, email, password, role , photo) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE accounts SET name = ?, surname = ?, patronymic = ?, email = ?, " +
            "password = ?, role = ?, photo = ? WHERE account_id = ?";

    private static final Logger LOGGER = LogManager.getLogger(AccountMySqlDao.class);
    private static final AccountActionMySqlDao ACCOUNT_ACTION_DAO =
            (AccountActionMySqlDao) DaoFactoryProvider.getDAOFactory(1).getAccountActionDao();

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
    public Account findByEmail(String email) throws DaoException {
        LOGGER.trace("findAllByEmail method is executed - email = " + email);
        List<Account> accounts = (List<Account>) findByParameter(SQL_SELECT_BY_EMAIL, email);
        return accounts.iterator().next();
    }

    @Override
    public Collection<Account> findAllByReporterAndAction(Account reporter, String actionType) {
        Account account;
        List<Account> accountList = new ArrayList<>();
        List<AccountAction> accountActionList;

        LOGGER.trace("findAllByReporterAndAction method is executed - " +
                "reporterID = " + reporter.getAccountId() + ", actionType = " + actionType);
        accountActionList = (List<AccountAction>) ACCOUNT_ACTION_DAO.findAllByReporterAndAction(
                reporter, actionType);
        for(AccountAction action : accountActionList) {
            account = findOne(action.getAccountId());
            accountList.add(account);
        }
        LOGGER.trace("found users by reporter - " + accountList.toString());
        return accountList;
    }
}
