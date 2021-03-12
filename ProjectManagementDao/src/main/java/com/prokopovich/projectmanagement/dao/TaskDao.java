package com.prokopovich.projectmanagement.dao;

import com.prokopovich.projectmanagement.model.Task;

import java.util.Collection;

public interface TaskDao extends GenericDao<Task> {

    boolean updateTask(int taskId);

    Collection<Task> findAllByProjectId(int projectId);

    Collection<Task> findAllByAssignee(int assignee);

    Collection<Task> findAllByReporter(int reporter);

    Collection<Task> findAllByCurrentStatus(String currentStatus);
}
