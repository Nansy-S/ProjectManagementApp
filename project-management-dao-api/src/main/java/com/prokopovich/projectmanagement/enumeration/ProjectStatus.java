package com.prokopovich.projectmanagement.enumeration;

public enum ProjectStatus {

    OPEN("Открыт"),
    IN_PROGRESS("В процессе"),
    CLOSED("Завершен"),
    DELETED("Удален");

    private String title;

    ProjectStatus(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
