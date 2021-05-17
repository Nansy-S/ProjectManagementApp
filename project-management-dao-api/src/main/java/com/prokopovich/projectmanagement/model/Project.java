package com.prokopovich.projectmanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY)
    //@OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "project_id")
    private List<ProjectAction> projectActions;

    public Project() { }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return projectId == project.projectId &&
                Objects.equals(projectCode, project.projectCode) &&
                Objects.equals(summary, project.summary) &&
                Objects.equals(dueDate, project.dueDate) &&
                Objects.equals(currentStatus, project.currentStatus);
    }

    @Override
    public int hashCode() {
        int result = projectId;
        result = 37 * result + (projectCode != null ? projectCode.hashCode() : 0);
        result = 37 * result + (summary != null ? summary.hashCode() : 0);
        result = 37 * result + (dueDate != null ? dueDate.hashCode() : 0);
        result = 37 * result + (currentStatus != null ? currentStatus.hashCode() : 0);
        return result;
    }
}
