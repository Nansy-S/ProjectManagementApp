package com.prokopovich.projectmanagement.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Project {

    private int projectId;
    private String projectCode;
    private String summary;
    private Timestamp dueDate;
    private String currentStatus;
    private List<ProjectAction> projectActions;

    public Project() {
        projectActions = new ArrayList<>();
    }

    public Project(int projectId, String projectCode, String summary, Timestamp dueDate, String currentStatus,
                   List<ProjectAction> projectActions) {
        this.projectId = projectId;
        this.projectCode = projectCode;
        this.summary = summary;
        this.dueDate = dueDate;
        this.currentStatus = currentStatus;
        this.projectActions = projectActions;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Timestamp getDueDate() {
        return dueDate;
    }

    public void setDueDate(Timestamp dueDate) {
        this.dueDate = dueDate;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }

    public List<ProjectAction> getProjectActions() {
        return projectActions;
    }

    public void setProjectActions(List<ProjectAction> projectActions) {
        this.projectActions = projectActions;
    }

    @Override
    public String toString() {
        return "Project: " +
                "projectId = " + projectId +
                ", projectCode = " + projectCode +
                ", summary = " + summary +
                ", dueDate = " + dueDate +
                ", currentStatus = " + currentStatus + ";";
    }
}
