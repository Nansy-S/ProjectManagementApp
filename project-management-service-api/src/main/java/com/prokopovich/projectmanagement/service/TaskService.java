package com.prokopovich.projectmanagement.service;

import com.prokopovich.projectmanagement.model.Account;
import com.prokopovich.projectmanagement.model.Task;

import java.util.List;

public interface TaskService {

    Task getByTaskId(int id);

    Task addNewTask(Task newTask, Account reporter);

    boolean editTask(Task task, Account reporter);

    Task changeStatus(int taskId, String newStatus, Account reporter);

    Task changeAssignee(int taskId, int newAssigneeId, Account reporterId);

    List<Task> getAllByProject(int projectId);

    List<Task> getAllByReporter(int userId);

    List<Task> getAllByAssignee(int userId);
}
