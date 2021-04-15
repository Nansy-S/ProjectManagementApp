package com.prokopovich.projectmanagement.dao;

import com.prokopovich.projectmanagement.exception.DaoException;
import com.prokopovich.projectmanagement.model.Account;
import com.prokopovich.projectmanagement.model.TaskAction;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

public class TaskActionMySqlDao extends BaseOperationMySqlDao<TaskAction> implements TaskActionDao {

    private static final String SQL_SELECT_ALL = "SELECT action_id, task_id, assignee_id FROM task_actions";
    private static final String SQL_SELECT_BY_TASK = "SELECT action_id, task_id, assignee_id FROM task_actions " +
            "WHERE task_id = ?";
    private static final String SQL_CREATE = "INSERT INTO task_actions (action_id, task_id, assignee_id) VALUES (?, ?, ?)";
    private static final Logger LOGGER = LogManager.getLogger(TaskActionMySqlDao.class);

    private final ActionDao actionDao;

    public TaskActionMySqlDao(ActionDao actionDao) {
        super();
        this.actionDao = actionDao;
    }

    @Override
    public String getSqlSelectAll() {
        return SQL_SELECT_ALL;
    }

    @Override
    public String getSqlCreate() {
        return SQL_CREATE;
    }

    @Override
    protected TaskAction getStatement(ResultSet rs) throws SQLException {
        TaskAction taskAction = new TaskAction();

        taskAction.setActionId(rs.getInt(1));
        taskAction.setTaskId(rs.getInt(2));
        taskAction.setAssigneeId(rs.getInt(3));
        taskAction.setAction(actionDao.findOne(taskAction.getActionId()));
        return taskAction;
    }

    @Override
    public void setStatement(TaskAction taskAction, PreparedStatement statement) throws SQLException {
        statement.setInt(1, taskAction.getActionId());
        statement.setInt(2, taskAction.getTaskId());
        statement.setInt(3, taskAction.getAssigneeId());
    }

    @Override
    public Collection<TaskAction> findAllByTaskId(int taskId) throws DaoException {
        LOGGER.trace("findAllByTaskId method is executed - taskId = " + taskId);
        return findByParameter(SQL_SELECT_BY_TASK, taskId);
    }

    public Collection<TaskAction> findAllByReporter(int reporterId) throws DaoException {
        return null;
    }
}
