package ru.rostanin.springbootdemo.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static ru.rostanin.springbootdemo.security.UserPermission.*;

public enum UserRole {
    USER(Set.of(MEDITATION_READ)),
    COMPANY(Set.of()),
    ADMIN(Set.of(USER_READ, USER_WRITE, USER_EDIT, MEDITATION_READ, MEDITATION_WRITE, MEDITATION_EDIT));

    private final Set<UserPermission> permissions;

    UserRole(Set<UserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<UserPermission> getPermissions() {
        return permissions;
    }

    public Set<GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = getPermissions().stream()
                .map(p -> new SimpleGrantedAuthority(p.getPermission()))
                .collect(Collectors.toSet());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
