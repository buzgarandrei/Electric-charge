package com.example.demo.entities;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//Stations:
//-pk id int
//-name str
//-adress str
//-price int[]
//-power_unit int[]
//-photos str[]
@Entity
@Table(name = "stations")
public class StationsEntity {

    @Id
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "adress")
    private String address;

    @Column(name = "price")
    private float price;

    @ManyToMany(mappedBy = "favourites")
    List<UsersEntity> liked = new ArrayList();

    @Column(name = "photos")
    private String Photos;

    @Column
    private double lat;

    @Column
    private double lng;

    @Column
    private double accuracy;

    @Column
    private String city;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "id_company")
    private CompaniesEntity company;

    public StationsEntity() {
    }

    public StationsEntity(String name, String address, float price, List<UsersEntity> liked, String photos, double lat, double lng, double accuracy, String city, CompaniesEntity company) {
        this.name = name;
        this.address = address;
        this.price = price;
        this.liked = liked;
        this.Photos = photos;
        this.lat = lat;
        this.lng = lng;
        this.accuracy = accuracy;
        this.city = city;
        this.company = company;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String adress) {
        this.address = adress;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public List<UsersEntity> getLiked() {
        return liked;
    }

    public void setLiked(List<UsersEntity> liked) {
        this.liked = liked;
    }

    public String getPhotos() {
        return Photos;
    }

    public void setPhotos(String photos) {
        Photos = photos;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public CompaniesEntity getCompany() {
        return company;
    }

    public void setCompany(CompaniesEntity company) {
        this.company = company;
    }
}
