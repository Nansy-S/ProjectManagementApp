package com.prokopovich.projectmanagement.service.impl;

import com.prokopovich.projectmanagement.dao.AccountDao;
import com.prokopovich.projectmanagement.dao.TaskDao;
import com.prokopovich.projectmanagement.enumeration.TaskActionType;
import com.prokopovich.projectmanagement.enumeration.TaskStatus;
import com.prokopovich.projectmanagement.enumeration.UserRole;
import com.prokopovich.projectmanagement.model.*;
import com.prokopovich.projectmanagement.service.ActionService;
import com.prokopovich.projectmanagement.service.TaskActionService;
import com.prokopovich.projectmanagement.service.TaskService;
import com.prokopovich.projectmanagement.util.ValidateTaskData;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {

    private final TaskDao taskDao;
    private final AccountDao accountDao;
    private final ActionService actionService;
    private final TaskActionService taskActionService;

    public TaskServiceImpl(TaskDao taskDao, AccountDao accountDao,
                           ActionService actionService, TaskActionService taskActionService) {
        this.taskDao = taskDao;
        this.accountDao = accountDao;
        this.actionService = actionService;
        this.taskActionService = taskActionService;
    }

    @Override
    public Task getByTaskId(int id) {
        Task taskInfo = taskDao.findOne(id);
        return taskInfo;
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

    @Override
    public Task changeStatus(int taskId, String newStatus, Account reporter) {
        ValidateTaskData validateData = new ValidateTaskData();
        Task currentTask = taskDao.findOne(taskId);
        if(validateData.validateChangeStatus(currentTask, newStatus, reporter)) {
            currentTask.setCurrentStatus(newStatus);
            if (taskDao.updateTask(currentTask)) {
                setTaskAction(currentTask.getTaskId(), reporter,
                        currentTask.getAssigneeInfo().getUserId(),
                        TaskStatus.getByTitle(currentTask.getCurrentStatus()).getTaskActionType());
                return currentTask;
            } else {
                return null;
            }
        }
        return null;
    }

    @Override
    public Task changeAssignee(int taskId, int newAssigneeId, Account reporter) {
        ValidateTaskData validateData = new ValidateTaskData();
        Task currentTask = taskDao.findOne(taskId);
        Account newAssignee = accountDao.findOne(newAssigneeId);
        if(validateData.validateChangeAssigneeRole(currentTask, newAssignee)) {
            currentTask.setAssignee(newAssigneeId);
            Task taskForUpdate = changeStatusByAssignee(currentTask, newAssignee);
            if (taskDao.updateTask(taskForUpdate)) {
                setTaskAction(taskForUpdate.getTaskId(), reporter,
                        taskForUpdate.getAssigneeInfo().getUserId(),
                        TaskActionType.CHANGE_ASSIGNEE.getTitle());
                return taskForUpdate;
            }
        }
        return null;
    }

    private Task changeStatusByAssignee(Task task, Account assignee) {
        String roleAssignee = assignee.getRole();
        if(roleAssignee.equals(UserRole.ROLE_DEVELOPER.getTitle()) &&
                !task.getCurrentStatus().equals(TaskStatus.OPEN.getTitle())) {
            task.setCurrentStatus(TaskStatus.OPEN.getTitle());
        }
        if(roleAssignee.equals(UserRole.ROLE_TESTER.getTitle()) &&
                !task.getCurrentStatus().equals(TaskStatus.READY_FOR_TEST.getTitle())) {
            task.setCurrentStatus(TaskStatus.READY_FOR_TEST.getTitle());
        }
        return task;
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

    @Override
    public List<Task> getAllByReporter(int userId) {
        return (List<Task>) taskDao.findAllByReporterAndAction(userId, TaskActionType.CREATE.getTitle());
    }

    @Override
    public List<Task> getAllByAssignee(int userId) {
        return (List<Task>) taskDao.findAllByAssignee(userId);
    }
}
