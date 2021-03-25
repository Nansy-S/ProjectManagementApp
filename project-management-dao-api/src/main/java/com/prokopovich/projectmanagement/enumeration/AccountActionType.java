package com.prokopovich.projectmanagement.enumeration;

public enum AccountActionType {

    CREATE("Create"),
    UPDATE("Update"),
    BLOCK("Block"),
    UNBLOCK("Unblock"),
    CHANGE_ROLE("Change role"),
    DEACTIVATION("Deactivation");

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
