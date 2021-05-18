package com.prokopovich.projectmanagement.enumeration;

public enum AccountStatus {

    ACTIVE("Активный"),
    LOCKED("Заблокирован"),
    DEACTIVATED("Не активный");

    private String title;
    private String accountActionType;

    AccountStatus(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public static AccountStatus getByTitle(String title) {
        for(AccountStatus value : AccountStatus.values()) {
            if(value.getTitle().equals(title)) {
                return value;
            }
        }
        throw new IllegalArgumentException("title");
    }

    public String getAccountActionType(String actionType) {
        switch (this) {
            case ACTIVE:
                if(actionType.equals("create")) {
                    accountActionType = AccountActionType.CREATE.getTitle();
                }
                if(actionType.equals("change")) {
                    accountActionType = AccountActionType.UNBLOCK.getTitle();
                }
                break;
            case LOCKED:
                accountActionType = AccountActionType.BLOCK.getTitle();
                break;
            case DEACTIVATED:
                accountActionType = AccountActionType.DEACTIVATION.getTitle();
                break;
            default:
                accountActionType = "Not determined";
        }
        return accountActionType;
    }
}
