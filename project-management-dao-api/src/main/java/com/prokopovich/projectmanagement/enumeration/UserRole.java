package com.prokopovich.projectmanagement.enumeration;

public enum UserRole {

    ADMIN("Administrator"),
    MANAGER("Project manager"),
    DEVELOPER("Developer"),
    TESTER("Tester");

    private final String title;

    UserRole(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
