package de.srh.library.constant;

public enum UserRole {

    STUDENT("S", "Student"),
    TEACHER("T", "Teacher"),
    ;

    private final String roleCode;
    private final String roleName;

    UserRole(String roleCode, String roleName) {
        this.roleCode = roleCode;
        this.roleName = roleName;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public String getRoleName() {
        return roleName;
    }

    public static UserRole getByRoleName(String roleName) {
        for (UserRole role : UserRole.values()) {
            if (role.getRoleName().equals(roleName)) {
                return role;
            }
        }
        throw new IllegalArgumentException("No matching for role: " + roleName);
    }

}
