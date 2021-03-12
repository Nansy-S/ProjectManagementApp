package com.prokopovich.projectmanagement.model;

import java.sql.Timestamp;

public class Action {
    private int actionId;
    private String type;
    private Timestamp datetime;
    private int reporter;

    public Action() {}

    public Action(int actionId, String type, Timestamp datetime, int reporter) {
        this.actionId = actionId;
        this.type = type;
        this.datetime = datetime;
        this.reporter = reporter;
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

    public int getReporter() {
        return reporter;
    }

    public void setReporter(int reporter) {
        this.reporter = reporter;
    }

    @Override
    public String toString() {
        return "\nAction: " +
                "id = " + actionId +
                ", type = " + type +
                ", datetime = " + datetime +
                ", reporter = " + reporter + ';';
    }
}
