package com.prokopovich.project_management.model;

import java.sql.Timestamp;

public class Comment {
    private int commentId;
    private String title;
    private String text;
    private Timestamp datetime;
    private String projectCode;
    private int taskCode;
    private int author;

    public Comment() { }

    public Comment(int commentId, String title, String text, Timestamp datetime, String projectCode, int taskCode, int author) {
        this.commentId = commentId;
        this.title = title;
        this.text = text;
        this.datetime = datetime;
        this.projectCode = projectCode;
        this.taskCode = taskCode;
        this.author = author;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Timestamp getDatetime() {
        return datetime;
    }

    public void setDatetime(Timestamp datetime) {
        this.datetime = datetime;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public int getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(int taskCode) {
        this.taskCode = taskCode;
    }

    public int getAuthor() {
        return author;
    }

    public void setAuthor(int author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Comment: " +
                "id =" + commentId +
                ", title = " + title +
                ", text = " + text +
                ", datetime = " + datetime +
                ", projectCode = " + projectCode +
                ", taskCode = " + taskCode +
                ", author = " + author + ';';
    }
}
