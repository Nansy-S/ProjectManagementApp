package com.prokopovich.projectmanagement.service.impl;

import com.prokopovich.projectmanagement.dao.ProjectActionDao;
import com.prokopovich.projectmanagement.model.Account;
import com.prokopovich.projectmanagement.model.ProjectAction;
import com.prokopovich.projectmanagement.service.ProjectActionService;

import java.util.List;

public class ProjectActionServiceImpl implements ProjectActionService {

    private final ProjectActionDao projectActionDao;

    public ProjectActionServiceImpl(ProjectActionDao projectActionDao) {
        this.projectActionDao = projectActionDao;
    }

    @Override
    public void addNewProjectAction(ProjectAction projectAction) {
        projectActionDao.create(projectAction);
    }

    @Override
    public List<ProjectAction> findAllByReporter(Account reporter) {
        return (List<ProjectAction>) projectActionDao.findAllByReporter(reporter.getAccountId());
    }
}
