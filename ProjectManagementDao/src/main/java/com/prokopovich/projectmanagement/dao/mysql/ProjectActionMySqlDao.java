package com.prokopovich.projectmanagement.dao.mysql;

import com.prokopovich.projectmanagement.dao.ProjectActionDao;
import com.prokopovich.projectmanagement.exception.DaoException;
import com.prokopovich.projectmanagement.model.ProjectAction;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

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
    private static final String SQL_CREATE = "INSERT INTO project_actions (project_id) VALUES (?)";
    private static final Logger LOGGER = LogManager.getLogger(ProjectActionMySqlDao.class);

    public ProjectActionMySqlDao(){
        super(new ProjectAction(), new ArrayList<ProjectAction>());
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
        return projectAction;
    }

    @Override
    public void setStatement(ProjectAction projectAction, PreparedStatement statement) throws SQLException {
        statement.setInt(1, projectAction.getProjectId());
    }

    @Override
    public Collection<ProjectAction> findAllByProjectId(int projectId) throws DaoException {
        LOGGER.trace("findAllByProjectId method is executed - projectId = " + projectId);
        List<ProjectAction> projectActionList = (List<ProjectAction>) findByParameter(SQL_SELECT_BY_PROJECT, projectId);
        return projectActionList;
    }
}
