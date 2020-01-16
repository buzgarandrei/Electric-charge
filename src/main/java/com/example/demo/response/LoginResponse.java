package com.example.demo.response;

public class LoginResponse {

    private Long id;
    private String username;
    private String role;
    private String token;
    boolean success;

    public LoginResponse() {
    }

    public LoginResponse(Long id, String username, String role, String token, boolean success) {
        this.id = id;
        this.username = username;
        this.role = role;
        this.token = token;
        this.success = success;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
