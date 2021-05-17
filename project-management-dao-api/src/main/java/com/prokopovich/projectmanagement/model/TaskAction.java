package com.prokopovich.projectmanagement.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "task_actions")
public class TaskAction implements Serializable {

    @Id
    @Column(name = "action_id")
    private int actionId;
    @Id
    @Column(name = "task_id")
    private int taskId;
    @Column(name = "assignee_id")
    private int assigneeId;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "action_id", updatable = false, insertable = false)
    private Action action;

    public TaskAction() { }

    public TaskAction(int actionId, int taskId, Action action, int assigneeId) {
        this.actionId = actionId;
        this.taskId = taskId;
        this.assigneeId = assigneeId;
        this.action = action;
    }

    public int getActionId() {
        return actionId;
    }

    public void setActionId(int actionId) {
        this.actionId = actionId;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public int getAssigneeId() {
        return assigneeId;
    }

    public void setAssigneeId(int assigneeId) {
        this.assigneeId = assigneeId;
    }

    @Override
    public String toString() {
        return "TaskAction: " +
                "actionId = " + actionId +
                ", taskId = " + taskId +
                ", assigneeId = " + assigneeId + ";";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskAction that = (TaskAction) o;
        return actionId == that.actionId &&
                taskId == that.taskId &&
                assigneeId == that.assigneeId;
    }

    @Override
    public int hashCode() {
        int result = actionId;
        result = 37 * result + taskId;
        result = 37 * result + assigneeId;
        return result;
    }
}
