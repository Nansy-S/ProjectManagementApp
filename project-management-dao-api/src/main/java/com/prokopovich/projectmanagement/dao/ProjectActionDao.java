package com.prokopovich.projectmanagement.dao;

import com.prokopovich.projectmanagement.exception.DaoException;
import com.prokopovich.projectmanagement.model.Account;
import com.prokopovich.projectmanagement.model.ProjectAction;

import java.util.Collection;

public interface ProjectActionDao extends GenericDao<ProjectAction> {

    Collection<ProjectAction> findAllByProjectId(int projectId) throws DaoException;

    Collection<ProjectAction> findAllByReporterAndAction(Account reporter, String actionType) throws DaoException;
}
