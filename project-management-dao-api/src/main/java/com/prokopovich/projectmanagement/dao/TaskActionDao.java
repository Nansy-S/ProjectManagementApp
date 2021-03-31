package com.prokopovich.projectmanagement.dao;

import com.prokopovich.projectmanagement.exception.DaoException;
import com.prokopovich.projectmanagement.model.Account;
import com.prokopovich.projectmanagement.model.TaskAction;

import java.sql.SQLException;
import java.util.Collection;

public interface TaskActionDao extends GenericDao<TaskAction> {

    Collection<TaskAction> findAllByTaskId(int taskId) throws SQLException;

    Collection<TaskAction> findAllByReporterAndAction(Account reporter, String actionType) throws DaoException;
}
