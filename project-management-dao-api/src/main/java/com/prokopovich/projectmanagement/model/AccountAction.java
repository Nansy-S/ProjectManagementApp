package com.prokopovich.projectmanagement.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "account_actions")
public class AccountAction implements Serializable {

    @Id
    @Column(name = "action_id")
    private int actionId;
    @Id
    @Column(name = "account_id")
    private int accountId;
    @Column(name = "reason")
    private String reason;
    @OneToOne
    @JoinColumn(name = "action_id")
    private Action action;

    public AccountAction(){
        action = new Action();
    }

    public AccountAction(int actionId, int accountId, String reason, Action action) {
        this.actionId = actionId;
        this.accountId = accountId;
        this.reason = reason;
        this.action = action;
    }

    public int getActionId() {
        return actionId;
    }

    public void setActionId(int actionId) {
        this.actionId = actionId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    @Override
    public String toString() {
        return "AccountAction: " +
                "actionId = " + actionId +
                ", accountId = " + accountId +
                ", reason = " + reason + ", " +
                action.toString() + ";";
    }
}
