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

    public static UserRole fromString(String text) {
        for (UserRole b : UserRole.values()) {
            if (b.title.equals(text)) {
                return b;
            }
        }
        return null;
    }
}
