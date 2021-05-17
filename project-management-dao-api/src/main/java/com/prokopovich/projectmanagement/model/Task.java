package com.prokopovich.projectmanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private int taskId;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_code")
    private String taskCode;
    @Column(name = "project_id")
    private int projectId;
    @Column(name = "priority")
    private String priority;
    @Column(name = "current_status")
    private String currentStatus;
    @Column(name = "due_date")
    private LocalDateTime dueDate;
    @Column(name = "estimation_time")
    private int estimationTime;
    @Column(name = "assignee")
    private int assignee;
    @Column(name = "description")
    private String description;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "assignee", referencedColumnName = "user_id", insertable = false, updatable = false)
    private User assigneeInfo;
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id", updatable = false, insertable = false)
    private List<TaskAction> taskActions;

    public Task() { }

    public Task(int taskId, String taskCode, int projectId, String priority, String currentStatus,
                LocalDateTime dueDate, int estimationTime, int assignee, String description,
                User assigneeInfo, List<TaskAction> taskActions) {
        this.taskId = taskId;
        this.taskCode = taskCode;
        this.projectId = projectId;
        this.priority = priority;
        this.currentStatus = currentStatus;
        this.dueDate = dueDate;
        this.estimationTime = estimationTime;
        this.assignee = assignee;
        this.description = description;
        this.assigneeInfo = assigneeInfo;
        this.taskActions = taskActions;
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

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getAssigneeInfo() {
        return assigneeInfo;
    }

    public void setAssigneeInfo(User assigneeInfo) {
        this.assigneeInfo = assigneeInfo;
    }

    public List<TaskAction> getTaskActions() {
        return taskActions;
    }

    public void setTaskActions(List<TaskAction> taskActions) {
        this.taskActions = taskActions;
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
                ", assignee = " + assignee +
                ", description = " + description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return taskId == task.taskId &&
                projectId == task.projectId &&
                estimationTime == task.estimationTime &&
                assignee == task.assignee &&
                Objects.equals(taskCode, task.taskCode) &&
                Objects.equals(priority, task.priority) &&
                Objects.equals(currentStatus, task.currentStatus) &&
                Objects.equals(dueDate, task.dueDate) &&
                Objects.equals(description, task.description);
    }

    @Override
    public int hashCode() {
        int result = taskId;
        result = 37 * result + (taskCode != null ? taskCode.hashCode() : 0);
        result = 37 * result + projectId;
        result = 37 * result + (priority != null ? priority.hashCode() : 0);
        result = 37 * result + (currentStatus != null ? currentStatus.hashCode() : 0);
        result = 37 * result + (dueDate != null ? dueDate.hashCode() : 0);
        result = 37 * result + estimationTime;
        result = 37 * result + assignee;
        result = 37 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
