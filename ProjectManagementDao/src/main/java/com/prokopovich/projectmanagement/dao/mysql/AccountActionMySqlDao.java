package com.prokopovich.projectmanagement.dao.mysql;

import com.prokopovich.projectmanagement.dao.AccountActionDao;
import com.prokopovich.projectmanagement.exception.DaoException;
import com.prokopovich.projectmanagement.model.AccountAction;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

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
    private static final String SQL_CREATE = "INSERT INTO account_actions (account_id, reason) VALUES (?, ?)";
    private static final Logger LOGGER = LogManager.getLogger(AccountActionMySqlDao.class);

    public AccountActionMySqlDao(){
        super(new AccountAction(), new ArrayList<AccountAction>());
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
    protected AccountAction getStatement(ResultSet rs) throws SQLException {
        AccountAction accountAction = new AccountAction();
        accountAction.setActionId(rs.getInt(1));
        accountAction.setAccountId(rs.getInt(2));
        accountAction.setReason(rs.getString(3));
        return accountAction;
    }

    @Override
    public void setStatement(AccountAction accountAction, PreparedStatement statement) throws SQLException {
        statement.setInt(1, accountAction.getAccountId());
        statement.setString(2, accountAction.getReason());
    }

    @Override
    public Collection<AccountAction> findAllByAccountId(int accountId) throws DaoException {
        LOGGER.trace("findAllByAccountId method is executed - accountId = " + accountId);
        List<AccountAction> accountActionList = (List<AccountAction>) findByParameter(SQL_SELECT_BY_ACCOUNT, accountId);
        return accountActionList;
    }
}
