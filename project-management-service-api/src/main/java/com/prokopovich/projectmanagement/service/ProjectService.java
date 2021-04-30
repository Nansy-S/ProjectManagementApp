package com.prokopovich.projectmanagement.service;

import com.prokopovich.projectmanagement.model.Account;
import com.prokopovich.projectmanagement.model.Project;

import java.util.List;

public interface ProjectService {

    Project addNewProject(Project newProject, Account reporter);

    boolean editProject(Project project, Account reporter);

    Project getByProjectId(int id);

    List<Project> getAllByReporterAndAction(int reporterId, String actionType);

    List<Project> getAllByReporterAndStatus(int reporterId, String ... statuses);

    boolean changeStatus(Project project, Account reporter, String typeAction);

    void setProjectStatusInProgress(int projectId);
}
