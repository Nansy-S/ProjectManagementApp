package com.prokopovich.projectmanagement.model;

import javax.persistence.*;

@Entity
@Table(name = "task_actions")
public class TaskAction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "action_id")
    private int actionId;
    @Column(name = "task_id")
    private int taskId;
    @Column(name = "assignee_id")
    private int assigneeId;
    @OneToOne
    @JoinColumn(name = "action_id")
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
}
