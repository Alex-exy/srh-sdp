package de.srh.library.constant;

public enum UserRole {

    STUDENT("S", "Student", 3, 1, 1),
    TEACHER("T", "Teacher", 6, 2, 1),
    ;

    private final String roleCode;
    private final String roleName;
    private final int maxBorrowCount;
    private final int maxBorrowAndExtendMonths;
    private final int maxExtensionCount;

    UserRole(String roleCode, String roleName, int maxBorrowCount, int maxBorrowAndExtendMonths, int maxExtensionCount) {
        this.roleCode = roleCode;
        this.roleName = roleName;
        this.maxBorrowCount = maxBorrowCount;
        this.maxBorrowAndExtendMonths = maxBorrowAndExtendMonths;
        this.maxExtensionCount = maxExtensionCount;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public String getRoleName() {
        return roleName;
    }

    public int getMaxBorrowCount() {
        return maxBorrowCount;
    }

    public int getMaxBorrowAndExtendMonths() {
        return maxBorrowAndExtendMonths;
    }

    public int getMaxExtensionCount() {
        return maxExtensionCount;
    }

    public static UserRole getByRoleName(String roleName) {
        for (UserRole role : UserRole.values()) {
            if (role.getRoleName().equals(roleName)) {
                return role;
            }
        }
        throw new IllegalArgumentException("No matching for role: " + roleName);
    }

    public static UserRole getByRoleCode(String userRole) {
        for (UserRole role : UserRole.values()) {
            if (role.getRoleCode().equals(userRole)) {
                return role;
            }
        }
        throw new IllegalArgumentException("No matching for role: " + userRole);
    }

}
