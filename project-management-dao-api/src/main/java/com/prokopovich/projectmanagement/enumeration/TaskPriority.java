package com.prokopovich.projectmanagement.enumeration;

import java.util.ArrayList;
import java.util.List;

public enum TaskPriority {

    BLOCKER("Наивысший приоритет"),
    CRITICAL("Критическая"),
    MAJOR("Важная"),
    NORMAL("Нормальная"),
    MINOR("Второстепенная");

    private String title;

    TaskPriority(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public static List<String> getAllTitle() {
        List<String> priorityTitleList = new ArrayList<>();
        for(TaskPriority value : TaskPriority.values()) {
            priorityTitleList.add(value.getTitle());
        }
        return priorityTitleList;
    }
}
