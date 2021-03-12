package com.prokopovich.projectmanagement.model;

public class AccountAction {
    private int actionId;
    private int accountId;
    private String reason;

    public AccountAction(){}

    public AccountAction(int actionId, int accountId, String reason) {
        this.actionId = actionId;
        this.accountId = accountId;
        this.reason = reason;
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

    @Override
    public String toString() {
        return "\nAccountAction: " +
                "actionId = " + actionId +
                ", accountId = " + accountId +
                ", reason = " + reason + ";";
    }
}
