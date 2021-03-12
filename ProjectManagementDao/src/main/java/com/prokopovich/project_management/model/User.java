package com.prokopovich.project_management.model;

public class User {
    private int userId;
    private String position;
    private String currentStatus;
    private String phone;

    public User(){}

    public User(int userId, String position, String currentStatus, String phone) {
        this.userId = userId;
        this.position = position;
        this.currentStatus = currentStatus;
        this.phone = phone;
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

    @Override
    public String toString() {
        return "User: " +
                "userId = " + userId +
                ", position = " + position +
                ", currentStatus = " + currentStatus +
                ", phone = " + phone + ';';
    }
}
