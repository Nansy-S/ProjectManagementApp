package com.prokopovich.projectmanagement.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "actions")
public class Action {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "action_id")
    private int actionId;
    @Column(name = "type")
    private String type;
    @Column(name = "date_time")
    private LocalDateTime datetime;
    @Column(name = "reporter")
    private int reporter;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id")
    private Account reporterInfo;

    public Action() {
        reporterInfo = new Account();
    }

    public Action(int actionId, String type, LocalDateTime datetime, int reporter) {
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

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    public int getReporter() {
        return reporter;
    }

    public void setReporter(int reporter) {
        this.reporter = reporter;
    }

    public Account getReporterInfo() {
        return reporterInfo;
    }

    public void setReporterInfo(Account reporterInfo) {
        this.reporterInfo = reporterInfo;
    }

    @Override
    public String toString() {
        return "Action: " +
                "id = " + actionId +
                ", type = " + type +
                ", datetime = " + datetime + ";";
    }
}
