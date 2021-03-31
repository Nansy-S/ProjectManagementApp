package com.prokopovich.projectmanagement.service.impl;

import com.prokopovich.projectmanagement.dao.TaskActionDao;
import com.prokopovich.projectmanagement.factory.DaoFactoryProvider;
import com.prokopovich.projectmanagement.model.Account;
import com.prokopovich.projectmanagement.model.TaskAction;
import com.prokopovich.projectmanagement.service.TaskActionService;

import java.util.List;

public class TaskActionServiceImpl implements TaskActionService {

    private static final TaskActionDao TASK_ACTION_DAO =
            DaoFactoryProvider.getDAOFactory(1).getTaskActionDao();

    @Override
    public void addNewTaskAction(TaskAction taskAction) {
        TASK_ACTION_DAO.create(taskAction, taskAction.getActionId());
    }

    @Override
    public List<TaskAction> findAllByReporter(Account reporter) {
        List<TaskAction> taskActions = (List<TaskAction>)
                TASK_ACTION_DAO.findAllByReporterAndAction(reporter, "");
        return taskActions;
    }
}
