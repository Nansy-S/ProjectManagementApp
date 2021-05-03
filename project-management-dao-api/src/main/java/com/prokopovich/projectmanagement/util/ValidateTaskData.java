package com.prokopovich.projectmanagement.util;

import com.prokopovich.projectmanagement.enumeration.TaskStatus;
import com.prokopovich.projectmanagement.enumeration.UserRole;
import com.prokopovich.projectmanagement.model.Account;
import com.prokopovich.projectmanagement.model.Task;

public class ValidateTaskData {

    public ValidateTaskData() { }

    public boolean validateChangeAssigneeRole(Task task, Account newAssignee) {
        if((task.getCurrentStatus().equals(TaskStatus.OPEN.getTitle()) ||
                task.getCurrentStatus().equals(TaskStatus.REOPENED.getTitle())) &&
                newAssignee.getRole().equals(UserRole.ROLE_DEVELOPER.getTitle())) {
            return true;
        }
        if(task.getCurrentStatus().equals(TaskStatus.RESOLVED.getTitle()) &&
                newAssignee.getRole().equals(UserRole.ROLE_TESTER.getTitle())) {
            return true;
        }
        if(task.getCurrentStatus().equals(TaskStatus.IN_PROGRESS.getTitle()) &&
                newAssignee.getRole().equals(UserRole.ROLE_DEVELOPER.getTitle())) {
            return true;
        }
        if(task.getCurrentStatus().equals(TaskStatus.READY_FOR_TEST.getTitle()) &&
                newAssignee.getRole().equals(UserRole.ROLE_TESTER.getTitle())) {
            return true;
        }
        if(task.getCurrentStatus().equals(TaskStatus.TESTED.getTitle()) &&
                newAssignee.getRole().equals(UserRole.ROLE_TESTER.getTitle())) {
            return true;
        }
        return false;
    }

    public boolean validateChangeStatus(Task task, String newStatus, Account currentUser) {
        if(currentUser.getRole().equals(UserRole.ROLE_MANAGER.getTitle())) {
            return true;
        }
        if(currentUser.getRole().equals(UserRole.ROLE_DEVELOPER.getTitle())) {
            if(validateChangeStatusDeveloper(task, newStatus)) {
                return true;
            }
        }
        if(currentUser.getRole().equals(UserRole.ROLE_TESTER.getTitle())) {
            if(validateChangeStatusTester(task, newStatus)) {
                return true;
            }
        }
        return false;
    }

    private boolean validateChangeStatusDeveloper(Task task, String newStatus) {
        if((task.getCurrentStatus().equals(TaskStatus.OPEN.getTitle()) ||
                task.getCurrentStatus().equals(TaskStatus.REOPENED.getTitle())) &&
                newStatus.equals(TaskStatus.IN_PROGRESS.getTitle())) {
            return true;
        }
        if(task.getCurrentStatus().equals(TaskStatus.IN_PROGRESS.getTitle()) &&
                newStatus.equals(TaskStatus.RESOLVED.getTitle())) {
            return true;
        }
        if(task.getCurrentStatus().equals(TaskStatus.RESOLVED.getTitle()) &&
                newStatus.equals(TaskStatus.READY_FOR_TEST.getTitle())) {
            return true;
        }
        return false;
    }

    private boolean validateChangeStatusTester(Task task, String newStatus) {
        if(task.getCurrentStatus().equals(TaskStatus.READY_FOR_TEST.getTitle()) &&
                newStatus.equals(TaskStatus.TESTED.getTitle())) {
            return true;
        }
        if(task.getCurrentStatus().equals(TaskStatus.TESTED.getTitle()) &&
                (task.getCurrentStatus().equals(TaskStatus.REOPENED.getTitle()) ||
                        task.getCurrentStatus().equals(TaskStatus.CLOSED.getTitle()))) {
            return true;
        }
        return false;
    }
}
