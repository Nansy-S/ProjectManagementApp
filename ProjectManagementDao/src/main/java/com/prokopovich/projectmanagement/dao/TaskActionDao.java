package com.prokopovich.projectmanagement.dao;

import com.prokopovich.projectmanagement.model.TaskAction;

import java.util.Collection;

public interface TaskActionDao extends GenericDao<TaskAction> {

    Collection<TaskAction> findAllByTaskId(int taskId);
}
