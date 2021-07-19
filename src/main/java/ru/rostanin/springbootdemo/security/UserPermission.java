package ru.rostanin.springbootdemo.security;

public enum UserPermission {
    USER_READ("user:read"),
    USER_WRITE("user:write"),
    USER_EDIT("user:edit"),

    MEDITATION_READ("meditation:read"),
    MEDITATION_WRITE("meditation:write"),
    MEDITATION_EDIT("meditation:edit");

    private final String permission;

    UserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
