package com.prokopovich.projectmanagement.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "project_actions")
public class ProjectAction implements Serializable {

    @Id
    @Column(name = "action_id")
    private int actionId;
    @Id
    @Column(name = "project_id")
    private int projectId;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "action_id", updatable = false, insertable = false)
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
