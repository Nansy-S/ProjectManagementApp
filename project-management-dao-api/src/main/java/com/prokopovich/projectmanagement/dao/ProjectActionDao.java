package com.prokopovich.projectmanagement.dao;

import com.prokopovich.projectmanagement.exception.DaoException;
import com.prokopovich.projectmanagement.model.Account;
import com.prokopovich.projectmanagement.model.ProjectAction;

import java.util.Collection;

public interface ProjectActionDao extends BaseOperationDao<ProjectAction> {

    Collection<ProjectAction> findAllByProjectId(int projectId) throws DaoException;

    Collection<ProjectAction> findAllByReporter(int reporter) throws DaoException;
}
