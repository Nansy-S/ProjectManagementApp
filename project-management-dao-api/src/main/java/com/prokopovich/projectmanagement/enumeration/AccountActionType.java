package com.prokopovich.projectmanagement.enumeration;

public enum AccountActionType {

    CREATE("Create"),
    UPDATE("Update"),
    LOCKING("Locking"),
    UNLOCKING("Unlocking"),
    DEACTIVATION("Deactivation");

    private String title;
    private String accountStatus;

    AccountActionType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getAccountStatus(){
        switch (this) {
        case CREATE:
        case UPDATE:
        case UNLOCKING:
            accountStatus = AccountStatus.ACTIVE.getTitle();
            break;
        case LOCKING:
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
