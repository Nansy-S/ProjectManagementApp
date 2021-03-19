package com.prokopovich.projectmanagement.enumeration;

public enum AccountStatus {

    ACTIVE("Active"),
    LOCKED("Locked"),
    DEACTIVATED("Not active");

    private String title;

    AccountStatus(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
