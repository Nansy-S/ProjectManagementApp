package com.prokopovich.projectmanagement.service;

import com.prokopovich.projectmanagement.model.Account;
import com.prokopovich.projectmanagement.model.Project;

import java.util.List;

public interface ProjectService {

    void addNewProject(Project newProject, Account reporter);

    boolean editProject(Project project, Account reporter);

    Project getByProjectId(int id);

    List<Project> getAllByReporterAndAction(Account reporter, String actionType);

    List<Project> getAllByReporterAndStatus(Account reporter, String ... statuses);

    boolean changeStatus(Project project, Account reporter, String typeAction);

    void setProjectStatusInProgress(int projectId);
}
