package com.prokopovich.projectmanagement.dao;

import com.prokopovich.projectmanagement.exception.DaoException;
import com.prokopovich.projectmanagement.factory.MySqlDaoFactory;
import com.prokopovich.projectmanagement.model.*;
import com.prokopovich.projectmanagement.util.LocalDateTimeAttributeConverter;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public class TaskMySqlDao extends GenericMySqlDaoWithHistory<Task> implements TaskDao {

    private static final String SQL_SELECT_ALL = "SELECT task_id, task_code, project_id, priority, current_status, " +
            "due_date, estimation_time, assignee, description FROM tasks";
    private static final String SQL_SELECT_ONE = "SELECT task_id, task_code, project_id, priority, current_status, " +
            "due_date, estimation_time, assignee, description FROM tasks WHERE task_id = ?";
    private static final String SQL_SELECT_BY_PROJECT = "SELECT task_id, task_code, project_id, priority, " +
            "current_status, due_date, estimation_time, assignee, description FROM tasks WHERE project_id = ?";
    private static final String SQL_SELECT_BY_ASSIGNEE = "SELECT task_id, task_code, project_id, priority, " +
            "current_status, due_date, estimation_time, assignee, description FROM tasks WHERE assignee = ?";
    private static final String SQL_SELECT_BY_REPORTER = "SELECT task_id, task_code, project_id, priority, " +
            "current_status, due_date, estimation_time, assignee, description FROM tasks WHERE reporter = ?";
    private static final String SQL_SELECT_BY_REPORTER_AND_ACTION = "SELECT task_id, task_code, project_id, " +
            "priority, current_status, due_date, estimation_time, assignee, description FROM tasks t " +
            "WHERE t.task_id IN (SELECT ta.task_id FROM task_actions ta INNER JOIN actions a " +
            "ON a.action_id = ta.action_id WHERE a.reporter = ? AND a.type = ?)";
    private static final String SQL_SELECT_BY_STATUS = "SELECT task_id, task_code, project_id, priority, " +
            "current_status, due_date, estimation_time, assignee, description FROM tasks WHERE current_status = ?";
    private static final String SQL_CREATE = "INSERT INTO tasks (task_code, project_id, priority, current_status, " +
            "due_date, estimation_time, assignee, description) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE tasks SET task_code = ?, project_id = ?, priority = ?, " +
            "current_status = ?, due_date = ?, estimation_time = ?, assignee = ?, description = ? " +
            "WHERE project_id = ?";

    private static final Logger LOGGER = LogManager.getLogger(TaskMySqlDao.class);
    private static final LocalDateTimeAttributeConverter CONVERTER = new LocalDateTimeAttributeConverter();

    private final ProjectDao projectDao;
    private final TaskActionDao taskActionDao;
    private final AttachmentDao attachmentDao;
    private final CommentDao commentDao;

    public TaskMySqlDao(ProjectDao projectDao, TaskActionDao taskActionDao, AttachmentDao attachmentDao,
                        CommentDao commentDao) {
        super();
        this.projectDao = projectDao;
        this.taskActionDao = taskActionDao;
        this.attachmentDao = attachmentDao;
        this.commentDao = commentDao;
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
    public String getSqlSelectByReporterAndAction() {
        return SQL_SELECT_BY_REPORTER_AND_ACTION;
    }

    @Override
    protected Task getStatement(ResultSet rs) throws SQLException {
        Task task = new Task();

        task.setTaskId(rs.getInt(1));
        task.setTaskCode(rs.getString(2));
        task.setProjectId(rs.getInt(3));
        task.setPriority(rs.getString(4));
        task.setCurrentStatus(rs.getString(5));
        task.setDueDate(CONVERTER.convertToEntityAttribute(rs.getTimestamp(6)));
        task.setEstimationTime(rs.getInt(7));
        task.setAssignee(rs.getInt(8));
        task.setDescription(rs.getString(9));
        //task.setProjectInfo(projectDao.findOne(task.getProjectId()));
        //task.setAttachmentList((List<Attachment>) attachmentDao.findAllByTaskId(task.getTaskId()));
        //task.setCommentList((List<Comment>) commentDao.findAllByTaskId(task.getTaskId()));
        task.setTaskActions((List<TaskAction>) taskActionDao.findAllByTaskId(task.getTaskId()));
        return task;
    }

    @Override
    public void setStatement(Task task, PreparedStatement statement) throws SQLException {
        statement.setString(1, task.getTaskCode());
        statement.setInt(2, task.getProjectId());
        statement.setString(3, task.getPriority());
        statement.setString(4, task.getCurrentStatus());
        statement.setTimestamp(5, CONVERTER.convertToDatabaseColumn(task.getDueDate()));
        statement.setInt(6, task.getEstimationTime());
        statement.setInt(7, task.getAssignee());
        statement.setString(8, task.getDescription());
    }

    @Override
    public boolean updateTask(Task task) throws DaoException {
        LOGGER.trace("updateTask method is executed");
        try (Connection connection = MySqlDaoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_UPDATE)) {
            setStatement(task, statement);
            statement.setInt(9, task.getTaskId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
        return true;
    }

    @Override
    public Collection<Task> findAllByProjectId(int projectId) throws DaoException {
        LOGGER.trace("findAllByProjectId method is executed - projectId = " + projectId);
        return findByParameter(SQL_SELECT_BY_PROJECT, projectId);
    }

    @Override
    public Collection<Task> findAllByAssignee(int assignee) throws DaoException {
        LOGGER.trace("findAllByAssignee method is executed - assignee = " + assignee);
        return findByParameter(SQL_SELECT_BY_ASSIGNEE, assignee);
    }
}
