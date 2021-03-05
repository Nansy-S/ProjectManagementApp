package com.prokopovich.project_management.model;

import java.sql.Timestamp;

public class Project {
    String projectCode;
    String summary;
    Timestamp createdDate;
    Timestamp updatedDate;
    Timestamp dueDate;
    String status;
    int teamId;

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
}
