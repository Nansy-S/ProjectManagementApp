package com.prokopovich.projectmanagement.service.impl;

import com.prokopovich.projectmanagement.dao.TaskDao;
import com.prokopovich.projectmanagement.enumeration.AccountActionType;
import com.prokopovich.projectmanagement.enumeration.TaskActionType;
import com.prokopovich.projectmanagement.enumeration.TaskStatus;
import com.prokopovich.projectmanagement.factory.DaoFactoryProvider;
import com.prokopovich.projectmanagement.factory.ServiceFactoryProvider;
import com.prokopovich.projectmanagement.model.*;
import com.prokopovich.projectmanagement.service.ActionService;
import com.prokopovich.projectmanagement.service.TaskActionService;
import com.prokopovich.projectmanagement.service.TaskService;

import java.time.LocalDateTime;
import java.util.List;

public class TaskServiceImpl implements TaskService {
    private static final TaskDao TASK_DAO = DaoFactoryProvider.getDAOFactory(1).getTaskDao();
    private static final ActionService ACTION_SERVICE =
            ServiceFactoryProvider.getServiceFactory(1).getActionServiceImpl();
    private static final TaskActionService TASK_ACTION_SERVICE =
            ServiceFactoryProvider.getServiceFactory(1).getTaskActionService();

    @Override
    public void addNewTask(Task newTask, Account reporter) {
        newTask.setCurrentStatus(TaskStatus.OPEN.getTitle());
        newTask.setAssignee(reporter.getAccountId());
        newTask = TASK_DAO.create(newTask, newTask.getTaskId());
        setTaskAction(newTask.getTaskId(), reporter, newTask.getAssignee(), TaskActionType.CREATE.getTitle());
    }

    @Override
    public boolean editTask(Task task, Account reporter) {
        if (TASK_DAO.updateTask(task)) {
            setTaskAction(task.getTaskId(), reporter, task.getAssignee(), TaskActionType.UPDATE.getTitle());
            return true;
        } else {
            return false;
        }
    }

    private void setTaskAction(int taskId, Account reporter, int assigneeId, String actionType) {
        Action newAction = new Action();
        TaskAction newTaskAction = new TaskAction();

        newAction.setType(actionType);
        newAction.setDatetime(LocalDateTime.now());
        newAction.setReporter(reporter.getAccountId());
        newAction.setReporterInfo(reporter);
        newAction = ACTION_SERVICE.addNewAction(newAction);

        newTaskAction.setActionId(newAction.getActionId());
        newTaskAction.setTaskId(taskId);
        newTaskAction.setAction(newAction);
        newTaskAction.setAssigneeId(assigneeId);
        TASK_ACTION_SERVICE.addNewTaskAction(newTaskAction);
    }

    @Override
    public List<Task> getAllByProject(int projectId) {
        return (List<Task>) TASK_DAO.findAllByProjectId(projectId);
    }
}
