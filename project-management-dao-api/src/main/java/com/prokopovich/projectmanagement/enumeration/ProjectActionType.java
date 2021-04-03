package com.prokopovich.projectmanagement.enumeration;

public enum ProjectActionType {

    CREATE("Create"),
    UPDATE("Update"),
    DELETE("Delete"),
    CLOSED("Closed");

    private String title;

    ProjectActionType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
