package com.prokopovich.projectmanagement.model;

import java.sql.Timestamp;

public class Task {

    private int taskId;
    private String taskCode;
    private int projectId;
    private String priority;
    private String currentStatus;
    private Timestamp dueDate;
    private int estimationTime;
    private int reporter;
    private int assignee;
    private String description;

    public Task() { }

    public Task(int taskId, String taskCode, int projectId, String priority, String currentStatus, Timestamp dueDate,
                int estimationTime, int reporter, int assignee, String description) {
        this.taskId = taskId;
        this.taskCode = taskCode;
        this.projectId = projectId;
        this.priority = priority;
        this.currentStatus = currentStatus;
        this.dueDate = dueDate;
        this.estimationTime = estimationTime;
        this.reporter = reporter;
        this.assignee = assignee;
        this.description = description;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
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

    public int getReporter() {
        return reporter;
    }

    public void setReporter(int reporter) {
        this.reporter = reporter;
    }

    public int getAssignee() {
        return assignee;
    }

    public void setAssignee(int assignee) {
        this.assignee = assignee;
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
                "taskId = " + taskId +
                ", taskCode = " + taskCode +
                ", projectId = " + projectId +
                ", priority = " + priority +
                ", currentStatus = " + currentStatus +
                ", dueDate = " + dueDate +
                ", estimationTime = " + estimationTime +
                ", reporter = " + reporter +
                ", assignee = " + assignee +
                ", description = " + description + ";";
    }
}
