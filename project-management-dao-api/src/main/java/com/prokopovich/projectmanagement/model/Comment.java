package com.prokopovich.projectmanagement.model;

import java.sql.Timestamp;

public class Comment {

    private int commentId;
    private String title;
    private String text;
    private Timestamp datetime;
    private int author;
    private int taskId;

    public Comment() { }

    public Comment(int commentId, String title, String text, Timestamp datetime, int author, int taskId) {
        this.commentId = commentId;
        this.title = title;
        this.text = text;
        this.datetime = datetime;
        this.author = author;
        this.taskId = taskId;
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

    public int getAuthor() {
        return author;
    }

    public void setAuthor(int author) {
        this.author = author;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public String toString() {
        return "Comment: " +
                "id =" + commentId +
                ", title = " + title +
                ", text = " + text +
                ", datetime = " + datetime +
                ", author = " + author +
                ", taskId = " + taskId + ";";
    }
}
