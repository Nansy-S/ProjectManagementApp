package com.prokopovich.projectmanagement.service;

import com.prokopovich.projectmanagement.model.Account;
import com.prokopovich.projectmanagement.model.Task;

import java.util.List;

public interface TaskService {

    void addNewTask(Task newTask, Account reporter);

    boolean editTask(Task task, Account reporter);

    List<Task> getAllByProject(int projectId);
}
