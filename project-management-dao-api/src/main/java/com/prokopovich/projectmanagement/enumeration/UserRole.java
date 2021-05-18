package com.prokopovich.projectmanagement.enumeration;

public enum UserRole {

    ROLE_ADMIN("Администратор"),
    ROLE_MANAGER("Менеджер проекта"),
    ROLE_DEVELOPER("Разработчик"),
    ROLE_TESTER("Тестировщик");

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
