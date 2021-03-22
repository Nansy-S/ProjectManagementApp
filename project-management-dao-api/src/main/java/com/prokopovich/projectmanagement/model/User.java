package com.prokopovich.projectmanagement.model;

import java.util.HashSet;
import java.util.Set;

public class User {

    private int userId;
    private String position;
    private String currentStatus;
    private String phone;
    private Account accountInfo;
    Set<AccountAction> accountActions;

    public User() {
        accountInfo = new Account();
        accountActions = new HashSet<>();
    }

    public User(int userId, String position, String currentStatus, String phone, Account accountInfo) {
        this.userId = userId;
        this.position = position;
        this.currentStatus = currentStatus;
        this.phone = phone;
        this.accountInfo = accountInfo;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Account getAccountInfo() {
        return accountInfo;
    }

    public void setAccountInfo(Account accountInfo) {
        this.accountInfo = accountInfo;
    }

    public Set<AccountAction> getAccountActions() {
        return accountActions;
    }

    public void setAccountActions(Set<AccountAction> accountActions) {
        this.accountActions = accountActions;
    }

    @Override
    public String toString() {
        return "User: " +
                "userId = " + userId +
                ", position = " + position +
                ", currentStatus = " + currentStatus +
                ", phone = " + phone + ";\n";
    }
}
