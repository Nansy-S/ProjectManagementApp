package com.prokopovich.projectmanagement.dao;

import com.prokopovich.projectmanagement.exception.DaoException;
import com.prokopovich.projectmanagement.model.Task;

import java.util.Collection;

public interface TaskDao extends GenericDaoWithHistory<Task> {

    boolean updateTask(Task task) throws DaoException;

    Collection<Task> findAllByProjectId(int projectId) throws DaoException;

    Collection<Task> findAllByAssignee(int reporter) throws DaoException;
}
