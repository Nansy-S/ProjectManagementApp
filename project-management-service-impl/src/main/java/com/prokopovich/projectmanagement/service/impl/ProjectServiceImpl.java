package com.prokopovich.projectmanagement.service.impl;

import com.prokopovich.projectmanagement.dao.ProjectDao;
import com.prokopovich.projectmanagement.enumeration.AccountActionType;
import com.prokopovich.projectmanagement.enumeration.ProjectActionType;
import com.prokopovich.projectmanagement.enumeration.ProjectStatus;
import com.prokopovich.projectmanagement.factory.DaoFactoryProvider;
import com.prokopovich.projectmanagement.factory.ServiceFactoryProvider;
import com.prokopovich.projectmanagement.model.*;
import com.prokopovich.projectmanagement.service.ActionService;
import com.prokopovich.projectmanagement.service.ProjectActionService;
import com.prokopovich.projectmanagement.service.ProjectService;

import java.time.LocalDateTime;
import java.util.List;

public class ProjectServiceImpl implements ProjectService {

    private static final ProjectDao PROJECT_DAO = DaoFactoryProvider.getDAOFactory(1).getProjectDao();
    private static final ActionService ACTION_SERVICE =
            ServiceFactoryProvider.getServiceFactory(1).getActionServiceImpl();
    private static final ProjectActionService PROJECT_ACTION_SERVICE =
            ServiceFactoryProvider.getServiceFactory(1).getProjectActionService();

    @Override
    public void addNewProject(Project newProject, Account reporter) {
        newProject.setCurrentStatus(ProjectStatus.OPEN.getTitle());
        newProject = PROJECT_DAO.create(newProject, newProject.getProjectId());
        setProjectAction(newProject.getProjectId(), reporter, ProjectActionType.CREATE.getTitle());
    }

    @Override
    public boolean editProject(Project project, Account reporter) {
        if (PROJECT_DAO.update(project)) {
            setProjectAction(project.getProjectId(), reporter, AccountActionType.UPDATE.getTitle());
            return true;
        } else {
            return false;
        }
    }

    private void setProjectAction(int projectId, Account reporter, String actionType) {
        Action newAction = new Action();
        ProjectAction newProjectAction = new ProjectAction();

        newAction.setType(actionType);
        newAction.setDatetime(LocalDateTime.now());
        newAction.setReporter(reporter.getAccountId());
        newAction.setReporterInfo(reporter);
        newAction = ACTION_SERVICE.addNewAction(newAction);

        newProjectAction.setActionId(newAction.getActionId());
        newProjectAction.setProjectId(projectId);
        newProjectAction.setAction(newAction);
        PROJECT_ACTION_SERVICE.addNewProjectAction(newProjectAction);
    }

    @Override
    public Project getByProjectId(int id) {
        return PROJECT_DAO.findOne(id);
    }

    @Override
    public List<Project> getAllByReporterAndAction(Account reporter, String actionType) {
        return (List<Project>) PROJECT_DAO.findAllByReporterAndAction(reporter, actionType);
    }

    @Override
    public List<Project> getAllByReporterAndStatus(Account reporter, String ... statuses) {
        return (List<Project>) PROJECT_DAO.findAllByReporterAndStatus(reporter, statuses);
    }

    @Override
    public boolean changeStatus(Project project, Account reporter, String typeAction) {
        if (PROJECT_DAO.update(project)) {
            setProjectAction(project.getProjectId(), reporter, typeAction);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void setProjectStatusInProgress(int projectId) {
        Project project = PROJECT_DAO.findOne(projectId);
        project.setCurrentStatus(ProjectStatus.IN_PROGRESS.getTitle());
        PROJECT_DAO.update(project);
    }
}
