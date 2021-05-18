package com.prokopovich.projectmanagement.enumeration;

public enum ProjectActionType {

    CREATE("Создание"),
    UPDATE("Редактирование"),
    DELETE("Удаление"),
    CLOSED("Завершение");

    private String title;

    ProjectActionType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
