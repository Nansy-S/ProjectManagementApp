package com.prokopovich.projectmanagement.service;

import com.prokopovich.projectmanagement.model.Account;
import com.prokopovich.projectmanagement.model.Task;

import java.util.List;

public interface TaskService {

    Task getByTaskId(int id);

    Task addNewTask(Task newTask, Account reporter);

    boolean editTask(Task task, Account reporter);

    Task changeStatus(Task task, Account reporterId);

    Task changeAssignee(Task task, Account reporterId);

    List<Task> getAllByProject(int projectId);
}
