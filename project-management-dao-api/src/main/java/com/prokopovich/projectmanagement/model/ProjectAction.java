package com.prokopovich.projectmanagement.model;

import javax.persistence.*;

@Entity
@Table(name = "project_actions")
public class ProjectAction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "action_id")
    private int actionId;
    @Column(name = "project_id")
    private int projectId;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "action_id")
    private Action action;

    public ProjectAction() { }

    public ProjectAction(int actionId, int projectId, Action action) {
        this.actionId = actionId;
        this.projectId = projectId;
        this.action = action;
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

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    @Override
    public String toString() {
        return "ProjectAction: " +
                "actionId = " + actionId +
                ", projectId = " + projectId + ";";
    }
}
