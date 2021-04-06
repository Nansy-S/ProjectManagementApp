package com.prokopovich.projectmanagement.model;

import javax.persistence.*;
import java.sql.Blob;

@Entity
@Table(name = "attachments")
public class Attachment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attachment_id")
    private int attachmentId;
    @Lob
    @Column(name = "file")
    private Blob file;
    @Column(name = "task_id")
    private int taskId;

    public Attachment() { }

    public Attachment(int attachmentId, Blob file, int taskId) {
        this.attachmentId = attachmentId;
        this.file = file;
        this.taskId = taskId;
    }

    public int getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(int attachmentId) {
        this.attachmentId = attachmentId;
    }

    public Blob getFile() {
        return file;
    }

    public void setFile(Blob file) {
        this.file = file;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public String toString() {
        return "Attachment: " +
                "id = " + attachmentId +
                ", file = " + file +
                ", taskId = " + taskId + ";";
    }
}
