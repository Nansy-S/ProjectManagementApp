package com.prokopovich.project_management.dao;

import com.prokopovich.project_management.model.Task;

import java.util.Collection;

public interface TaskDAO {
    int createTask(Task task);
    boolean updateTask(String projectCode, int taskCode);
    Task findTask(String projectCode, int taskCode);
    Collection<Task> findAllByProjectCode(String projectCode);
    Collection<Task> findAllByAssignee(int assignee);
    Collection<Task> findAllByReporter(int reporter);
    Collection<Task> findAllByCurrentStatus(String currentStatus);
}
