package com.prokopovich.projectmanagement.enumeration;

public enum ProjectStatus {

    OPEN("Open"),
    IN_PROGRESS("In progress"),
    CLOSED("Closed"),
    DELETED("Deleted");

    private String title;

    ProjectStatus(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
