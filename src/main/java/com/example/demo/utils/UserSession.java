package com.example.demo.utils;

import com.example.demo.entities.enums.RoleEnum;

public class UserSession {

    private Long userId;
    private String username;
    private String token;
    private RoleEnum roleEnum;

    public UserSession() {
    }

    public UserSession(Long userId, String username, String token, RoleEnum roleEnum) {
        this.userId = userId;
        this.username = username;
        this.token = token;
        this.roleEnum = roleEnum;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public RoleEnum getRoleEnum() {
        return roleEnum;
    }

    public void setRoleEnum(RoleEnum roleEnum) {
        this.roleEnum = roleEnum;
    }
}
