package com.prokopovich.projectmanagement.model;

public class TaskAction {

    private int actionId;
    private int taskId;

    public TaskAction() { }

    public TaskAction(int actionId, int taskId) {
        this.actionId = actionId;
        this.taskId = taskId;
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

    @Override
    public String toString() {
        return "TaskAction: " +
                "actionId = " + actionId +
                ", taskId = " + taskId + ";\n";
    }
}
