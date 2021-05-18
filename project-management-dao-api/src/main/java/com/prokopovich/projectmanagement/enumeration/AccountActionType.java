package com.prokopovich.projectmanagement.enumeration;

public enum AccountActionType {

    CREATE("Создание"),
    UPDATE("Редактирование"),
    BLOCK("Блокировка"),
    UNBLOCK("Разблокировка"),
    CHANGE_ROLE("Смена роли"),
    DEACTIVATION("Деактивация");

    private String title;
    private String accountStatus;

    AccountActionType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getAccountStatus() {
        switch (this) {
        case CREATE:
        case UPDATE:
        case UNBLOCK:
            accountStatus = AccountStatus.ACTIVE.getTitle();
            break;
        case BLOCK:
            accountStatus = AccountStatus.LOCKED.getTitle();
            break;
        case DEACTIVATION:
            accountStatus = AccountStatus.DEACTIVATED.getTitle();
            break;
        default:
            accountStatus = "Not determined";
        }
        return accountStatus;
    }
}
