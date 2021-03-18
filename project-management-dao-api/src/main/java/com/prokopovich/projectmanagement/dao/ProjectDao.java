package com.prokopovich.projectmanagement.dao;

import com.prokopovich.projectmanagement.exception.DaoException;
import com.prokopovich.projectmanagement.model.Project;

import java.util.Collection;

public interface ProjectDao extends GenericDao<Project> {

    boolean updateProject(Project project) throws DaoException;

    Collection<Project> findAllByCurrentStatus(String currentStatus) throws DaoException;
}
