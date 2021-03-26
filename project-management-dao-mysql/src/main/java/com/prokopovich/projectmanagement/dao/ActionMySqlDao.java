package com.prokopovich.projectmanagement.dao;

import com.prokopovich.projectmanagement.exception.DaoException;
import com.prokopovich.projectmanagement.model.Action;
import com.prokopovich.projectmanagement.util.LocalDateTimeAttributeConverter;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ActionMySqlDao extends GenericMySqlDao<Action> implements ActionDao {

    private static final String SQL_SELECT_ALL = "SELECT action_id, type, date_time, reporter FROM actions";
    private static final String SQL_SELECT_ONE = "SELECT action_id, type, date_time, reporter FROM actions " +
            "WHERE action_id = ?";
    private static final String SQL_SELECT_BY_REPORTER = "SELECT action_id, type, date_time, reporter FROM actions " +
            "WHERE reporter = ?";
    private static final String SQL_SELECT_BY_TYPE = "SELECT action_id, type, date_time, reporter FROM actions " +
            "WHERE type = ?";
    private static final String SQL_CREATE = "INSERT INTO actions (type, date_time, reporter) VALUES (?, ?, ?)";

    private static final Logger LOGGER = LogManager.getLogger(ActionMySqlDao.class);
    private static final LocalDateTimeAttributeConverter CONVERTER = new LocalDateTimeAttributeConverter();

    private final AccountDao accountDao;

    public ActionMySqlDao() {
        super(new Action(), new ArrayList<Action>());
        accountDao = new AccountMySqlDao();
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
    protected Action getStatement(ResultSet rs) throws SQLException {
        Action action = new Action();

        action.setActionId(rs.getInt(1));
        action.setType(rs.getString(2));
        action.setDatetime(CONVERTER.convertToEntityAttribute(rs.getTimestamp(3)));
        action.setReporter(rs.getInt(4));
        action.setReporterInfo(accountDao.findOne(action.getReporter()));
        return action;
    }

    @Override
    public void setStatement(Action action, PreparedStatement statement) throws SQLException {
        statement.setString(1, action.getType());
        statement.setTimestamp(2, CONVERTER.convertToDatabaseColumn(action.getDatetime()));
        statement.setInt(3, action.getReporter());
    }

    @Override
    public Collection<Action> findAllByReporter(int reporter) throws DaoException {
        LOGGER.trace("findAllByReporter method is executed - reporterId = " + reporter);
        List<Action> actionList = (List<Action>) findByParameter(SQL_SELECT_BY_REPORTER, reporter);
        return actionList;
    }

    @Override
    public Collection<Action> findAllByType(String type) throws DaoException {
        LOGGER.trace("findAllByType method is executed - type = " + type);
        List<Action> actionList = (List<Action>) findByParameter(SQL_SELECT_BY_TYPE, type);
        return actionList;
    }
}
