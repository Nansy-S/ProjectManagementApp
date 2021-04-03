package com.prokopovich.projectmanagement.enumeration;

public enum TaskActionType {

    CREATE("Create"),
    UPDATE("Update"),
    START("Start"),
    RESOLVE("Resolve"),
    READY_FOR_TEST("Ready for Test"),
    CHANGE_STATUS("Change status"),
    CHANGE_ASSIGNEE("Change Assignee");

    private String title;
    private String accountStatus;

    TaskActionType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
