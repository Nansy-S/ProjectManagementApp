package com.prokopovich.projectmanagement.service.impl;

import com.prokopovich.projectmanagement.dao.TaskDao;
import com.prokopovich.projectmanagement.enumeration.TaskActionType;
import com.prokopovich.projectmanagement.enumeration.TaskStatus;
import com.prokopovich.projectmanagement.model.*;
import com.prokopovich.projectmanagement.service.ActionService;
import com.prokopovich.projectmanagement.service.TaskActionService;
import com.prokopovich.projectmanagement.service.TaskService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {
    private final TaskDao taskDao;
    private final ActionService actionService;
    private final TaskActionService taskActionService;

    public TaskServiceImpl(TaskDao taskDao, ActionService actionService, TaskActionService taskActionService) {
        this.taskDao = taskDao;
        this.actionService = actionService;
        this.taskActionService = taskActionService;
    }

    @Override
    public Task addNewTask(Task newTask, Account reporter) {
        newTask.setTaskCode("code");
        newTask.setCurrentStatus(TaskStatus.OPEN.getTitle());
        newTask.setAssignee(reporter.getAccountId());
        newTask = taskDao.create(newTask);
        newTask = taskDao.findOne(newTask.getTaskId());
        setTaskAction(newTask.getTaskId(), reporter, newTask.getAssignee(), TaskActionType.CREATE.getTitle());
        return newTask;
    }

    @Override
    public boolean editTask(Task task, Account reporter) {
        if (taskDao.updateTask(task)) {
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
        newAction = actionService.addNewAction(newAction);

        newTaskAction.setActionId(newAction.getActionId());
        newTaskAction.setTaskId(taskId);
        newTaskAction.setAction(newAction);
        newTaskAction.setAssigneeId(assigneeId);
        taskActionService.addNewTaskAction(newTaskAction);
    }

    @Override
    public List<Task> getAllByProject(int projectId) {
        return (List<Task>) taskDao.findAllByProjectId(projectId);
    }
}
