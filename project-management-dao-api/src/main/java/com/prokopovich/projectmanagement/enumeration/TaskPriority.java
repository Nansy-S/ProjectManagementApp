package com.prokopovich.projectmanagement.enumeration;

import java.util.ArrayList;
import java.util.List;

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

    public static List<String> getAllTitle() {
        List<String> priorityTitleList = new ArrayList<>();
        for(TaskPriority value : TaskPriority.values()) {
            priorityTitleList.add(value.getTitle());
        }
        return priorityTitleList;
    }
}
