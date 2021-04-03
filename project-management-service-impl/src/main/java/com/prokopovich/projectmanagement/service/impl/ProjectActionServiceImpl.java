package com.prokopovich.projectmanagement.service.impl;

import com.prokopovich.projectmanagement.dao.ProjectActionDao;
import com.prokopovich.projectmanagement.factory.DaoFactoryProvider;
import com.prokopovich.projectmanagement.model.Account;
import com.prokopovich.projectmanagement.model.ProjectAction;
import com.prokopovich.projectmanagement.service.ProjectActionService;

import java.util.List;

public class ProjectActionServiceImpl implements ProjectActionService {

    private static final ProjectActionDao PROJECT_ACTION_DAO =
            DaoFactoryProvider.getDAOFactory(1).getProjectActionDao();

    @Override
    public void addNewProjectAction(ProjectAction projectAction) {
        PROJECT_ACTION_DAO.create(projectAction, projectAction.getActionId());
    }

    @Override
    public List<ProjectAction> findAllByReporter(Account reporter) {
        List<ProjectAction> projectActions = (List<ProjectAction>)
                PROJECT_ACTION_DAO.findAllByReporterAndAction(reporter, "");
        return projectActions;
    }
}
