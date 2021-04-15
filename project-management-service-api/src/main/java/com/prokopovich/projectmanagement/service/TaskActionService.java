package com.prokopovich.projectmanagement.service;

import com.prokopovich.projectmanagement.model.TaskAction;

import java.util.List;

public interface TaskActionService {

    void addNewTaskAction(TaskAction taskAction);

    List<TaskAction> findAllByReporter(int reporterId);
}
