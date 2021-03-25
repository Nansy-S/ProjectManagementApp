package com.prokopovich.projectmanagement.dao;

import com.prokopovich.projectmanagement.exception.DaoException;
import com.prokopovich.projectmanagement.model.TaskAction;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TaskActionMySqlDao extends GenericMySqlDao<TaskAction> implements TaskActionDao {

    private static final String SQL_SELECT_ALL = "SELECT action_id, task_id FROM task_actions";
    private static final String SQL_SELECT_ONE = "SELECT action_id, task_id FROM task_actions WHERE action_id = ?";
    private static final String SQL_SELECT_BY_TASK = "SELECT action_id, task_id FROM task_actions WHERE task_id = ?";
    private static final String SQL_CREATE = "INSERT INTO task_actions (task_id) VALUES (?)";
    private static final Logger LOGGER = LogManager.getLogger(TaskActionMySqlDao.class);

    public TaskActionMySqlDao(){
        super(new TaskAction(), new ArrayList<TaskAction>());
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
    protected TaskAction getStatement(ResultSet rs) throws SQLException {
        TaskAction taskAction = new TaskAction();

        taskAction.setActionId(rs.getInt(1));
        taskAction.setTaskId(rs.getInt(2));
        return taskAction;
    }

    @Override
    public void setStatement(TaskAction taskAction, PreparedStatement statement) throws SQLException {
        statement.setInt(1, taskAction.getTaskId());
    }

    @Override
    public Collection<TaskAction> findAllByTaskId(int taskId) throws DaoException {
        LOGGER.trace("findAllByTaskId method is executed - taskId = " + taskId);
        List<TaskAction> taskActionList = (List<TaskAction>) findByParameter(SQL_SELECT_BY_TASK, taskId);
        return taskActionList;
    }
}
