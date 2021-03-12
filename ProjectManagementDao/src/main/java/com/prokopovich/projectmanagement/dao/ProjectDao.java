package com.prokopovich.projectmanagement.dao;

import com.prokopovich.projectmanagement.model.Project;

import java.util.Collection;

public interface ProjectDao extends GenericDao<Project> {

    boolean updateProject(String projectCode);

    Collection<Project> findAllByCurrentStatus(String currentStatus);
}
