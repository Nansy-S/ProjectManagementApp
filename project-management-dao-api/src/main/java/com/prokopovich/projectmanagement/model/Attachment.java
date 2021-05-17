package com.prokopovich.projectmanagement.model;

import javax.persistence.*;
import java.sql.Blob;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Attachment that = (Attachment) o;
        return attachmentId == that.attachmentId &&
                taskId == that.taskId &&
                Objects.equals(file, that.file);
    }

    @Override
    public int hashCode() {
        int result = attachmentId;
        result = 37 * result + (file != null ? file.hashCode() : 0);
        result = 37 * result + taskId;
        return result;
    }
}
