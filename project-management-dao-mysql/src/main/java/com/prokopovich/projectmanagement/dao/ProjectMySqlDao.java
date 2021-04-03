package com.prokopovich.projectmanagement.dao;

import com.prokopovich.projectmanagement.enumeration.DatabaseType;
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

public class ProjectMySqlDao extends GenericMySqlDaoWithHistory<Project> implements ProjectDao {

    private static final String SQL_SELECT_ALL = "SELECT project_id, project_code, summary, due_date, current_status " +
            "FROM projects";
    private static final String SQL_SELECT_ONE = "SELECT project_id, project_code, summary, due_date, current_status " +
            "FROM projects WHERE project_id = ?";
    private static final String SQL_SELECT_BY_STATUS = "SELECT project_id, project_code, summary, due_date, " +
            "current_status FROM projects WHERE current_status = ?";
    private static final String SQL_SELECT_BY_REPORTER_AND_ACTION = "SELECT project_id, project_code, summary, " +
            "due_date, current_status FROM projects p WHERE p.project_id IN " +
            "(SELECT pa.project_id FROM project_actions pa INNER JOIN actions a ON a.action_id = pa.action_id " +
            "WHERE a.reporter = ? AND a.type = ?)";
    private static final String SQL_CREATE = "INSERT INTO projects (project_code, summary, due_date, current_status) " +
            "VALUES (?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE projects SET project_code = ?, summary = ?, due_date = ?, "+
            "current_status = ? WHERE project_id = ?";

    private static final Logger LOGGER = LogManager.getLogger(ProjectMySqlDao.class);
    private static final LocalDateTimeAttributeConverter CONVERTER = new LocalDateTimeAttributeConverter();

    private final ProjectActionDao projectActionDao = new ProjectActionMySqlDao();

    public ProjectMySqlDao() {
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
    protected Project getStatement(ResultSet rs) throws SQLException {
        Project project = new Project();

        project.setProjectId(rs.getInt(1));
        project.setProjectCode(rs.getString(2));
        project.setSummary(rs.getString(3));
        project.setDueDate(CONVERTER.convertToEntityAttribute(rs.getTimestamp(4)));
        project.setCurrentStatus(rs.getString(5));
        project.setProjectActions((List<ProjectAction>) projectActionDao.findAllByProjectId(project.getProjectId()));
        return project;
    }

    @Override
    public void setStatement(Project project, PreparedStatement statement) throws SQLException {
        statement.setString(1, project.getProjectCode());
        statement.setString(2, project.getSummary());
        statement.setTimestamp(3, CONVERTER.convertToDatabaseColumn(project.getDueDate()));
        statement.setString(4, project.getCurrentStatus());
    }

    @Override
    public boolean update(Project project) throws DaoException {
        LOGGER.trace("update project method is executed");
        try (Connection connection = MySqlDaoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_UPDATE)) {
            setStatement(project, statement);
            statement.setInt(5, project.getProjectId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
        return true;
    }

    @Override
    public Collection<Project> findAllByCurrentStatus(String currentStatus) throws DaoException {
        LOGGER.trace("findAllByCurrentStatus method is executed - currentStatus = " + currentStatus);
        List<Project> projects = (List<Project>) findByParameter(SQL_SELECT_BY_STATUS, currentStatus);
        return projects;
    }

    @Override
    public Collection<Project> findAllByReporterAndStatus(Account reporter, String ... statuses) throws DaoException {
        Project project;
        List<Project> projectList = new ArrayList<>();
        List<ProjectAction> projectActionList;

        LOGGER.trace("findAllByReporterAndAction method is executed - " +
                "reporterID = " + reporter.getAccountId() + ", project status = " + statuses.toString());
        projectActionList = (List<ProjectAction>) projectActionDao.findAllByReporterAndAction(
                reporter, "Create");
        for (ProjectAction action : projectActionList) {
            project = findOne(action.getProjectId());
            for (String status : statuses) {
                if (project.getCurrentStatus().equals(status)) {
                    projectList.add(project);
                }
            }
        }
        return projectList;
    }
}
