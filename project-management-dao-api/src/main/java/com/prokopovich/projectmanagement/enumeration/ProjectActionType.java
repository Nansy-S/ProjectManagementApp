package com.prokopovich.projectmanagement.enumeration;

public enum ProjectActionType {

    CREATE("Create"),
    UPDATE("Update"),
    CHANGE_STATUS("Change status");

    private String title;
    private String accountStatus;

    ProjectActionType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
