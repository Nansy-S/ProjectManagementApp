package com.prokopovich.project_management.model;

import java.sql.Timestamp;

public class AccountAction {
    private int accountActionId;
    private int adminId;
    private int userId;
    private String type;
    private Timestamp datetime;
    private String reason;

    public AccountAction(){}

    public AccountAction(int accountActionId, int adminId, int userId, String type, Timestamp datetime, String reason) {
        this.accountActionId = accountActionId;
        this.adminId = adminId;
        this.userId = userId;
        this.type = type;
        this.datetime = datetime;
        this.reason = reason;
    }

    public int getAccountActionId() {
        return accountActionId;
    }

    public void setAccountActionId(int accountActionId) {
        this.accountActionId = accountActionId;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "AccountAction: " +
                "id = " + accountActionId +
                ", adminId = " + adminId +
                ", userId = " + userId +
                ", type = " + type +
                ", datetime = " + datetime +
                ", reason = " + reason + ';';
    }
}
