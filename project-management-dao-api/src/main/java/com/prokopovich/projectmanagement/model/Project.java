package com.prokopovich.projectmanagement.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private int projectId;
    @Column(name = "project_code")
    private String projectCode;
    @Column(name = "summary")
    private String summary;
    @Column(name = "due_date")
    private LocalDateTime dueDate;
    @Column(name = "current_status")
    private String currentStatus;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "project_id")
    private List<ProjectAction> projectActions;

    public Project() {
        projectActions = new ArrayList<>();
    }

    public Project(int projectId, String projectCode, String summary, LocalDateTime dueDate, String currentStatus,
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

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
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
