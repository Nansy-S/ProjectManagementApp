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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ProjectActionMySqlDao extends GenericMySqlDao<ProjectAction> implements ProjectActionDao {

    private static final String SQL_SELECT_ALL = "SELECT action_id, project_id FROM project_actions";
    private static final String SQL_SELECT_ONE = "SELECT action_id, project_id FROM project_actions " +
            "WHERE action_id = ?";
    private static final String SQL_SELECT_BY_PROJECT = "SELECT action_id, project_id FROM project_actions " +
            "WHERE project_id = ?";
    private static final String SQL_SELECT_BY_REPORTER_AND_ACTION = "SELECT a.action_id, a.type, a.date_time, a.reporter, " +
            "pa.project_id FROM project_actions pa INNER JOIN actions a ON a.action_id = pa.action_id " +
            "WHERE a.reporter = ? AND a.type LIKE ?";
    private static final String SQL_CREATE = "INSERT INTO project_actions (action_id, project_id) VALUES (?, ?)";

    private static final Logger LOGGER = LogManager.getLogger(ProjectActionMySqlDao.class);
    private static final LocalDateTimeAttributeConverter CONVERTER = new LocalDateTimeAttributeConverter();

    private final ActionMySqlDao actionDao;

    public ProjectActionMySqlDao() {
        super(new ProjectAction(), new ArrayList<ProjectAction>());
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
    protected ProjectAction getStatement(ResultSet rs) throws SQLException {
        ProjectAction projectAction = new ProjectAction();

        projectAction.setActionId(rs.getInt(1));
        projectAction.setProjectId(rs.getInt(2));
        projectAction.setAction(actionDao.findOne(projectAction.getActionId()));
        return projectAction;
    }

    @Override
    public void setStatement(ProjectAction projectAction, PreparedStatement statement) throws SQLException {
        statement.setInt(1, projectAction.getActionId());
        statement.setInt(2, projectAction.getProjectId());
    }

    @Override
    public Collection<ProjectAction> findAllByProjectId(int projectId) throws DaoException {
        LOGGER.trace("findAllByProjectId method is executed - projectId = " + projectId);
        List<ProjectAction> projectActionList = (List<ProjectAction>) findByParameter(SQL_SELECT_BY_PROJECT, projectId);
        return projectActionList;
    }

    @Override
    public Collection<ProjectAction> findAllByReporterAndAction(Account reporter, String actionType) throws DaoException {
        ProjectAction projectAction = new ProjectAction();
        Collection<ProjectAction> projectActionList = new ArrayList<>();

        LOGGER.trace("findAllByReporterAndAction method is executed - " +
                "reporterID = " + reporter.getAccountId() + ", action = " + actionType);
        try (Connection connection = MySqlDaoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_REPORTER_AND_ACTION)) {
            statement.setInt(1, reporter.getAccountId());
            statement.setString(2, "%" + actionType);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                projectAction.setAction(actionDao.getStatement(rs));
                projectAction.setActionId(rs.getInt(1));
                projectAction.setProjectId(rs.getInt(5));
                projectActionList.add(projectAction);
                LOGGER.debug("found actions: " + projectAction.toString());
            }
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
        return projectActionList;
    }
}
