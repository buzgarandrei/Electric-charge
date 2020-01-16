package com.example.demo.entities;

import javax.persistence.*;

@Entity
@Table(name = "regions", uniqueConstraints = @UniqueConstraint(columnNames = {"country","city"}))
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String country;

    @Column
    private String city;

    public Region() {
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
