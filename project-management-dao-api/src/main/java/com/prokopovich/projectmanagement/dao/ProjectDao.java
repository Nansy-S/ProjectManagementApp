package com.prokopovich.projectmanagement.dao;

import com.prokopovich.projectmanagement.exception.DaoException;
import com.prokopovich.projectmanagement.model.Account;
import com.prokopovich.projectmanagement.model.Project;
import com.prokopovich.projectmanagement.model.ProjectAction;
import com.prokopovich.projectmanagement.model.User;

import java.util.Collection;

public interface ProjectDao extends GenericDaoWithHistory<Project> {

    boolean update(Project project) throws DaoException;

    Collection<Project> findAllByCurrentStatus(String currentStatus) throws DaoException;

    Collection<Project> findAllByReporterAndStatus(Account reporter, String ... statuses) throws DaoException;
}
