package com.prokopovich.projectmanagement.service.impl;

import com.prokopovich.projectmanagement.dao.TaskActionDao;
import com.prokopovich.projectmanagement.model.Account;
import com.prokopovich.projectmanagement.model.TaskAction;
import com.prokopovich.projectmanagement.service.TaskActionService;

import java.util.List;

public class TaskActionServiceImpl implements TaskActionService {

    private final TaskActionDao taskActionDao;

    public TaskActionServiceImpl(TaskActionDao taskActionDao) {
        this.taskActionDao = taskActionDao;
    }

    @Override
    public void addNewTaskAction(TaskAction taskAction) {
        taskActionDao.create(taskAction);
    }

    @Override
    public List<TaskAction> findAllByReporter(int reporterId) {
        return (List<TaskAction>) taskActionDao.findAllByReporter(reporterId);
    }
}
