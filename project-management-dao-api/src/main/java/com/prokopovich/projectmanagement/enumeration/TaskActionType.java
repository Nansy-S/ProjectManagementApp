package com.prokopovich.projectmanagement.enumeration;

public enum TaskActionType {

    CREATE("Создание"),
    UPDATE("Редактирование"),
    START("Начато выполнение"),
    RESOLVE("Реализация"),
    REOPEN("Переоткрытие"),
    CLOSED("Закрытие"),
    READY_FOR_TEST("Готова к тестированию"),
    START_TEST("Начато тестирование"),
    CHANGE_STATUS("Смена статуса"),
    CHANGE_ASSIGNEE("Смена исполнителя");

    private String title;

    TaskActionType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
