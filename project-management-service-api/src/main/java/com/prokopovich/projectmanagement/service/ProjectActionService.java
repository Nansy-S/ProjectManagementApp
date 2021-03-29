package com.prokopovich.projectmanagement.service;

import com.prokopovich.projectmanagement.model.Account;
import com.prokopovich.projectmanagement.model.ProjectAction;

import java.util.List;

public interface ProjectActionService {

    void addNewProjectAction(ProjectAction projectAction);

    List<ProjectAction> findAllByReporter(Account reporter);
}
