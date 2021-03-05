package com.prokopovich.project_management.model;

import java.sql.Timestamp;

public class Task {
    private int taskCode;
    private String projectCode;
    private String priority;
    private String currentStatus;
    private Timestamp dueDate;
    private int estimationTime;
    private int assignee;
    private int reporter;
    private String description;

    public Task() {}

    public Task(int taskCode, String projectCode, String priority, String currentStatus, Timestamp dueDate,
                int estimationTime, int assignee, int reporter, String description) {
        this.taskCode = taskCode;
        this.projectCode = projectCode;
        this.priority = priority;
        this.currentStatus = currentStatus;
        this.dueDate = dueDate;
        this.estimationTime = estimationTime;
        this.assignee = assignee;
        this.reporter = reporter;
        this.description = description;
    }

    public int getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(int taskCode) {
        this.taskCode = taskCode;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }

    public Timestamp getDueDate() {
        return dueDate;
    }

    public void setDueDate(Timestamp dueDate) {
        this.dueDate = dueDate;
    }

    public int getEstimationTime() {
        return estimationTime;
    }

    public void setEstimationTime(int estimationTime) {
        this.estimationTime = estimationTime;
    }

    public int getAssignee() {
        return assignee;
    }

    public void setAssignee(int assignee) {
        this.assignee = assignee;
    }

    public int getReporter() {
        return reporter;
    }

    public void setReporter(int reporter) {
        this.reporter = reporter;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Task: " +
                "taskCode = " + taskCode +
                ", projectCode = " + projectCode +
                ", priority = " + priority +
                ", currentStatus = " + currentStatus +
                ", dueDate = " + dueDate +
                ", estimationTime = " + estimationTime +
                ", assignee = " + assignee +
                ", reporter = " + reporter +
                ", description = " + description + ';';
    }
}
