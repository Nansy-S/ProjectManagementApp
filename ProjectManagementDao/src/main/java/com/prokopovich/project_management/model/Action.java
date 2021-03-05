package com.prokopovich.project_management.model;

import java.sql.Timestamp;

public class Action {
    int actionId;
    String type;
    Timestamp datetime;
    int reporterAction;
    int taskCode;
    String projectCode;

    public Action() {}

    public Action(int actionId, String type, Timestamp datetime, int reporterAction, int taskCode, String projectCode) {
        this.actionId = actionId;
        this.type = type;
        this.datetime = datetime;
        this.reporterAction = reporterAction;
        this.taskCode = taskCode;
        this.projectCode = projectCode;
    }

    public int getActionId() {
        return actionId;
    }

    public void setActionId(int actionId) {
        this.actionId = actionId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Timestamp getDatetime() {
        return datetime;
    }

    public void setDatetime(Timestamp datetime) {
        this.datetime = datetime;
    }

    public int getReporterAction() {
        return reporterAction;
    }

    public void setReporterAction(int reporterAction) {
        this.reporterAction = reporterAction;
    }

    public int getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(int taskCode) {
        this.taskCode = taskCode;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    @Override
    public String toString() {
        return "Action: " +
                "id = " + actionId +
                ", type = " + type +
                ", datetime = " + datetime +
                ", reporter = " + reporterAction +
                ", taskCode = " + taskCode +
                ", projectCode = " + projectCode + ';';
    }
}
