package com.epam.web.entity;

import com.epam.web.enums.Role;

public class User implements Identifiable {

    public final static String TABLE_NAME = "user";
    private final Long id;
    private final String username;
    private final String password;
    private final Role role;
    private final boolean isBlocked;

    public User(Long id, String username, String password, Role role, boolean isBlocked) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.isBlocked = isBlocked;
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public boolean getIsBlocked() {
        return isBlocked;
    }
}
