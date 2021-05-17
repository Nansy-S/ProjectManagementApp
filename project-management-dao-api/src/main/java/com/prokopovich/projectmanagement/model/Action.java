package com.prokopovich.projectmanagement.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

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
    @JoinColumn(name = "reporter", referencedColumnName = "account_id", insertable = false, updatable = false)
    private Account reporterInfo;

    public Action() { }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Action action = (Action) o;
        return actionId == action.actionId &&
                reporter == action.reporter &&
                Objects.equals(type, action.type) &&
                Objects.equals(datetime, action.datetime);
    }

    @Override
    public int hashCode() {
        int result = actionId;
        result = 37 * result + (type != null ? type.hashCode() : 0);
        result = 37 * result + (datetime != null ? datetime.hashCode() : 0);
        result = 37 * result + reporter;
        return result;
    }
}
