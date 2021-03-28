package com.prokopovich.projectmanagement.enumeration;

public enum TaskPriority {

    BLOCKER("Blocker"),
    CRITICAL("Critical"),
    MAJOR("Major"),
    NORMAL("Normal"),
    MINOR("Minor");

    private String title;

    TaskPriority(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
