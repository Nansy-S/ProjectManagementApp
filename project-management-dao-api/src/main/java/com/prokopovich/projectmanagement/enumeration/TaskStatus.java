package com.prokopovich.projectmanagement.enumeration;

public enum TaskStatus {

    OPEN("Открыта"),
    IN_PROGRESS("В процессе"),
    RESOLVED("Реализована"),
    REOPENED("Переоткрыта"),
    READY_FOR_TEST("Готова к тестированию"),
    TESTED("Тестируется"),
    CLOSED("Закрыта");

    private String title;
    private String taskActionType;

    TaskStatus(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public static TaskStatus getByTitle(String title) {
        for(TaskStatus value : TaskStatus.values()) {
            if(value.getTitle().equals(title)) {
                return value;
            }
        }
        throw new IllegalArgumentException("title");
    }

    public String getTaskActionType() {
        switch (this) {
            case OPEN:
                taskActionType = TaskActionType.CREATE.getTitle();
                break;
            case IN_PROGRESS:
                taskActionType = TaskActionType.START.getTitle();
                break;
            case RESOLVED:
                taskActionType = TaskActionType.RESOLVE.getTitle();
                break;
            case REOPENED:
                taskActionType = TaskActionType.REOPEN.getTitle();
                break;
            case READY_FOR_TEST:
                taskActionType = TaskActionType.READY_FOR_TEST.getTitle();
                break;
            case TESTED:
                taskActionType = TaskActionType.START_TEST.getTitle();
                break;
            case CLOSED:
                taskActionType = TaskActionType.CLOSED.getTitle();
                break;
            default:
                taskActionType = "Not determined";
        }
        return taskActionType;
    }
}
