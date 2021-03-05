package com.prokopovich.project_management.dao;

import com.prokopovich.project_management.model.Project;

import java.util.Collection;

public interface ProjectDAO {
    int createProject(Project project);
    boolean updateProject(String projectCode);
    Project findProject(String projectCode);
    Collection<Project> findAll();
    Collection<Project> findAllByReporter(int reporter);
    Collection<Project> findAllByStatus(String status);
}
