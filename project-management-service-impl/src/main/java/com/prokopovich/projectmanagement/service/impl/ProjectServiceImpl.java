package com.prokopovich.projectmanagement.service.impl;

import com.prokopovich.projectmanagement.dao.ProjectDao;
import com.prokopovich.projectmanagement.enumeration.AccountActionType;
import com.prokopovich.projectmanagement.enumeration.ProjectActionType;
import com.prokopovich.projectmanagement.enumeration.ProjectStatus;
import com.prokopovich.projectmanagement.model.*;
import com.prokopovich.projectmanagement.service.ActionService;
import com.prokopovich.projectmanagement.service.ProjectActionService;
import com.prokopovich.projectmanagement.service.ProjectService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectDao projectDao;
    private final ActionService actionService;
    private final ProjectActionService projectActionService;

    public ProjectServiceImpl(ProjectDao projectDao, ActionService actionService,
                              ProjectActionService projectActionService) {
        this.projectDao = projectDao;
        this.actionService = actionService;
        this.projectActionService = projectActionService;
    }

    @Override
    public void addNewProject(Project newProject, Account reporter) {
        newProject.setCurrentStatus(ProjectStatus.OPEN.getTitle());
        int newProjectId = projectDao.create(newProject);
        setProjectAction(newProjectId, reporter, ProjectActionType.CREATE.getTitle());
    }

    @Override
    public boolean editProject(Project project, Account reporter) {
        if (projectDao.update(project)) {
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
        newAction = actionService.addNewAction(newAction);

        newProjectAction.setActionId(newAction.getActionId());
        newProjectAction.setProjectId(projectId);
        newProjectAction.setAction(newAction);
        projectActionService.addNewProjectAction(newProjectAction);
    }

    @Override
    public Project getByProjectId(int id) {
        return projectDao.findOne(id);
    }

    @Override
    public List<Project> getAllByReporterAndAction(int reporterId, String actionType) {
        return (List<Project>) projectDao.findAllByReporterAndAction(reporterId, actionType);
    }

    @Override
    public List<Project> getAllByReporterAndStatus(int reporterId, String ... statuses) {
        return (List<Project>) projectDao.findAllByReporterAndStatus(reporterId, statuses);
    }

    @Override
    public boolean changeStatus(Project project, Account reporter, String typeAction) {
        if (projectDao.update(project)) {
            setProjectAction(project.getProjectId(), reporter, typeAction);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void setProjectStatusInProgress(int projectId) {
        Project project = projectDao.findOne(projectId);
        project.setCurrentStatus(ProjectStatus.IN_PROGRESS.getTitle());
        projectDao.update(project);
    }
}
