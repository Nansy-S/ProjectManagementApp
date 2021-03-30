package com.prokopovich.projectmanagement.dao;

import com.prokopovich.projectmanagement.exception.DaoException;
import com.prokopovich.projectmanagement.factory.MySqlDaoFactory;
import com.prokopovich.projectmanagement.model.Account;
import com.prokopovich.projectmanagement.model.AccountAction;
import com.prokopovich.projectmanagement.util.LocalDateTimeAttributeConverter;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AccountActionMySqlDao extends GenericMySqlDao<AccountAction> implements AccountActionDao {

    private static final String SQL_SELECT_ALL = "SELECT action_id, account_id, reason FROM account_actions";
    private static final String SQL_SELECT_ONE = "SELECT action_id, account_id, reason FROM account_actions " +
            "WHERE action_id = ?";
    private static final String SQL_SELECT_BY_ACCOUNT = "SELECT action_id, account_id, reason FROM account_actions " +
            "WHERE account_id = ?";
    private static final String SQL_SELECT_BY_REPORTER_AND_ACTION = "SELECT aa.action_id, aa.account_id, aa.reason, " +
            "a.type, a.date_time FROM account_actions aa INNER JOIN actions a ON a.action_id = aa.action_id " +
            "WHERE a.reporter = ? AND a.type LIKE ?";
    private static final String SQL_CREATE = "INSERT INTO account_actions (action_id, account_id, reason) " +
            "VALUES (?, ?, ?)";

    private static final Logger LOGGER = LogManager.getLogger(AccountActionMySqlDao.class);
    private static final LocalDateTimeAttributeConverter CONVERTER = new LocalDateTimeAttributeConverter();

    private final ActionDao actionDao;

    public AccountActionMySqlDao() {
        super();
        actionDao = new ActionMySqlDao();
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
    protected AccountAction getStatement(ResultSet rs) throws SQLException {
        AccountAction accountAction = new AccountAction();

        accountAction.setActionId(rs.getInt(1));
        accountAction.setAccountId(rs.getInt(2));
        accountAction.setReason(rs.getString(3));
        accountAction.setAction(actionDao.findOne(accountAction.getActionId()));
        return accountAction;
    }

    @Override
    public void setStatement(AccountAction accountAction, PreparedStatement statement) throws SQLException {
        statement.setInt(1, accountAction.getAction().getActionId());
        statement.setInt(2, accountAction.getAccountId());
        statement.setString(3, accountAction.getReason());
    }

    @Override
    public Collection<AccountAction> findAllByAccountId(int accountId) throws DaoException {
        LOGGER.trace("findAllByAccountId method is executed - accountId = " + accountId);
        List<AccountAction> accountActionList = (List<AccountAction>) findByParameter(SQL_SELECT_BY_ACCOUNT, accountId);
        return accountActionList;
    }

    @Override
    public Collection<AccountAction> findAllByReporterAndAction(Account reporter, String actionType)
                throws DaoException {
        AccountAction accountAction;
        List<AccountAction> accountActionList = new ArrayList<>();

        LOGGER.trace("findUserIdByReporterAndAction method is executed - " +
                "reporterID = " + reporter.getAccountId() + ", action = " + actionType);
        try (Connection connection = MySqlDaoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_REPORTER_AND_ACTION)) {
            statement.setInt(1, reporter.getAccountId());
            statement.setString(2, "%" + actionType);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                accountAction = getStatement(rs);
                accountAction.getAction().setActionId(accountAction.getActionId());
                accountAction.getAction().setType(rs.getString(4));
                accountAction.getAction().setDatetime(
                        CONVERTER.convertToEntityAttribute(rs.getTimestamp(5)));
                accountAction.getAction().setReporter(reporter.getAccountId());
                accountAction.getAction().setReporterInfo(reporter);
                accountActionList.add(accountAction);
            }
            LOGGER.debug("found actions: " + accountActionList.toString());
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
        return accountActionList;
    }
}
