package com.prokopovich.projectmanagement.enumeration;

public enum TaskStatus {

    OPEN("Open"),
    IN_PROGRESS("In progress"),
    READY_FOR_TEST("Ready for Test"),
    CLOSED("Closed");

    private String title;

    TaskStatus(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
