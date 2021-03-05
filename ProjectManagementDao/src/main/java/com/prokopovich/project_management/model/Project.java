package com.prokopovich.project_management.model;

import java.sql.Timestamp;

public class Project {
    private String projectCode;
    private String summary;
    private Timestamp createdDate;
    private Timestamp updatedDate;
    private Timestamp dueDate;
    private String status;
    private int teamId;

    public Project() {}

    public Project(String projectCode, String summary, Timestamp createdDate, Timestamp updatedDate, Timestamp dueDate,
                   String status, int teamId) {
        this.projectCode = projectCode;
        this.summary = summary;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.dueDate = dueDate;
        this.status = status;
        this.teamId = teamId;
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

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public Timestamp getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Timestamp updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Timestamp getDueDate() {
        return dueDate;
    }

    public void setDueDate(Timestamp dueDate) {
        this.dueDate = dueDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    @Override
    public String toString() {
        return "Project: " +
                "projectCode = " + projectCode +
                ", summary = " + summary +
                ", createdDate = " + createdDate +
                ", updatedDate = " + updatedDate +
                ", dueDate = " + dueDate +
                ", status = " + status +
                ", teamId = " + teamId + ';';
    }
}
