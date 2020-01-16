package com.example.demo.response;

public class UsersResponse {

    private Long id;
    private String role;
    private String username;
    private String name;
    private String city;

    public UsersResponse() {
    }

    public UsersResponse(Long id, String role, String username, String name, String city) {
        this.id = id;
        this.role = role;
        this.username = username;
        this.name = name;
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
