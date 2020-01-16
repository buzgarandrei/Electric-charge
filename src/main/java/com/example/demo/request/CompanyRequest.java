package com.example.demo.request;

public class CompanyRequest {

    private Long id;
    private String name;

    public CompanyRequest() {
    }

    public CompanyRequest(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
