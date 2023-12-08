package de.srh.library.constant;

public enum UserStatus {
    ACTIVE("A", "Active"),
    OVERDUE("O", "Overdue"),
    FROZEN("F", "Frozen"),
    INACTIVE("I", "Inactive"),
    ;

    private final String userStatusCode;
    private final String userStatusName;

    UserStatus(String userStatusCode, String userStatusName) {
        this.userStatusCode = userStatusCode;
        this.userStatusName = userStatusName;
    }

    public String getUserStatusCode() {
        return userStatusCode;
    }

    public String getUserStatusName() {
        return userStatusName;
    }
}
