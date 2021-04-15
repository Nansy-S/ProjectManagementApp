package com.prokopovich.projectmanagement.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private int taskId;
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
    @ManyToOne
    @JoinColumn(name = "project_id", insertable = false, updatable = false)
    private Project projectInfo;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User assigneeInfo;
    @OneToMany
    @JoinColumn(name = "task_id")
    private List<Attachment> attachmentList;
    @OneToMany
    @JoinColumn(name = "task_id")
    private List<Comment> commentList;
    @OneToMany
    @JoinColumn(name = "task_id")
    private List<TaskAction> taskActions;

    public Task() {
        projectInfo = new Project();
        assigneeInfo = new User();
        attachmentList = new ArrayList<>();
        commentList = new ArrayList<>();
        taskActions = new ArrayList<>();
    }

    public Task(int taskId, String taskCode, int projectId, String priority, String currentStatus, LocalDateTime dueDate,
                int estimationTime, int assignee, String description, Project projectInfo, User assigneeInfo,
                List<Attachment> attachmentList, List<Comment> commentList, List<TaskAction> taskActions) {
        this.taskId = taskId;
        this.taskCode = taskCode;
        this.projectId = projectId;
        this.priority = priority;
        this.currentStatus = currentStatus;
        this.dueDate = dueDate;
        this.estimationTime = estimationTime;
        this.assignee = assignee;
        this.description = description;
        this.projectInfo = projectInfo;
        this.assigneeInfo = assigneeInfo;
        this.attachmentList = attachmentList;
        this.commentList = commentList;
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

    public Project getProjectInfo() {
        return projectInfo;
    }

    public void setProjectInfo(Project projectInfo) {
        this.projectInfo = projectInfo;
    }

    public User getAssigneeInfo() {
        return assigneeInfo;
    }

    public void setAssigneeInfo(User assigneeInfo) {
        this.assigneeInfo = assigneeInfo;
    }

    public List<Attachment> getAttachmentList() {
        return attachmentList;
    }

    public void setAttachmentList(List<Attachment> attachmentList) {
        this.attachmentList = attachmentList;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
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
}
