package com.prokopovich.projectmanagement.enumeration;

public enum TaskActionType {

    CREATE("Create"),
    UPDATE("Update"),
    START("Start"),
    RESOLVE("Resolve"),
    REOPEN("Reopen"),
    CLOSED("Closed"),
    READY_FOR_TEST("Ready for Test"),
    CHANGE_STATUS("Change Status"),
    CHANGE_ASSIGNEE("Change Assignee");

    private String title;

    TaskActionType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
