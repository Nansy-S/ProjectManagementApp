package com.prokopovich.project_management.model;

import java.sql.Blob;

public class Attachment {
    private int attachmentId;
    private Blob file;
    private int taskCode;
    private String projectCode;
    private int userId;

    public Attachment() {}

    public Attachment(int attachmentId, Blob file, int taskCode, String projectCode, int userId) {
        this.attachmentId = attachmentId;
        this.file = file;
        this.taskCode = taskCode;
        this.projectCode = projectCode;
        this.userId = userId;
    }

    public int getAttachment_id() {
        return attachmentId;
    }

    public void setAttachment_id(int attachmentId) {
        this.attachmentId = attachmentId;
    }

    public Blob getFile() {
        return file;
    }

    public void setFile(Blob file) {
        this.file = file;
    }

    public int getTask_code() {
        return taskCode;
    }

    public void setTask_code(int taskCode) {
        this.taskCode = taskCode;
    }

    public String getProject_code() {
        return projectCode;
    }

    public void setProject_code(String projectCode) {
        this.projectCode = projectCode;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Attachment: " +
                "id = " + attachmentId +
                ", file = " + file +
                ", task_code = " + taskCode +
                ", project_code = " + projectCode +
                ", userId = " + userId + ';';
    }
}
