package com.prokopovich.projectmanagement.dao;

import com.prokopovich.projectmanagement.exception.DaoException;
import com.prokopovich.projectmanagement.model.Task;

import java.util.Collection;

public interface TaskDao extends GenericDao<Task> {

    boolean updateTask(Task task) throws DaoException;

    Collection<Task> findAllByProjectId(int projectId) throws DaoException;

    Collection<Task> findAllByAssignee(int assignee) throws DaoException;

    Collection<Task> findAllByReporter(int reporter) throws DaoException;

    Collection<Task> findAllByCurrentStatus(String currentStatus) throws DaoException;
}
