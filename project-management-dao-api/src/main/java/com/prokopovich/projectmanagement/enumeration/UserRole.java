package com.prokopovich.projectmanagement.enumeration;

public enum UserRole {

    ROLE_ADMIN("Administrator"),
    ROLE_MANAGER("Project manager"),
    ROLE_DEVELOPER("Developer"),
    ROLE_TESTER("Tester");

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
