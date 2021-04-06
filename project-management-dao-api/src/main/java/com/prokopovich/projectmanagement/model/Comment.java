package com.prokopovich.projectmanagement.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private int commentId;
    @Column(name = "title")
    private String title;
    @Column(name = "text")
    private String text;
    @Column(name = "date_time")
    private Timestamp datetime;
    @Column(name = "author")
    private int author;
    @Column(name = "task_id")
    private int taskId;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User authorInfo;


    public Comment() { }

    public Comment(int commentId, String title, String text, Timestamp datetime, int author, int taskId, User authorInfo) {
        this.commentId = commentId;
        this.title = title;
        this.text = text;
        this.datetime = datetime;
        this.author = author;
        this.taskId = taskId;
        this.authorInfo = authorInfo;
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

    public User getAuthorInfo() {
        return authorInfo;
    }

    public void setAuthorInfo(User authorInfo) {
        this.authorInfo = authorInfo;
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
