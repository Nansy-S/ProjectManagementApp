package com.prokopovich.project_management.model;

public class ProjectAction {
    private int actionId;
    private int projectId;

    public ProjectAction() { }

    public ProjectAction(int actionId, int projectId) {
        this.actionId = actionId;
        this.projectId = projectId;
    }

    public int getActionId() {
        return actionId;
    }

    public void setActionId(int actionId) {
        this.actionId = actionId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    @Override
    public String toString() {
        return "\nProjectAction: " +
                "actionId = " + actionId +
                ", projectId = " + projectId + ';';
    }
}
