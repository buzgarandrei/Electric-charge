package com.example.demo.response;

import javax.persistence.Column;

public class RegionResponse {

    private Long id;
    private String country;
    private String city;

    public RegionResponse() {
    }

    public RegionResponse(Long id, String country, String city) {
        this.id = id;
        this.country = country;
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
