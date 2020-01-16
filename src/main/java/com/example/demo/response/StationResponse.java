package com.example.demo.response;

import com.example.demo.utils.LatLng;

public class StationResponse {

    private Long id;
    private String name;
    private String address;
    private float price;
    private String Photos;
    private LatLng latLng;
    private double accuracy;
    private String city;
    private Long idCompany;

    public StationResponse() {
    }

    public StationResponse(Long id, String name, String address, float price, String photos, LatLng latLng, double accuracy, String city, Long idCompany) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.price = price;
        this.Photos = photos;
        this.accuracy = accuracy;
        this.latLng = latLng;
        this.city = city;
        this.idCompany = idCompany;
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

    public void setAddress(String address) {
        this.address = address;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getPhotos() {
        return Photos;
    }

    public void setPhotos(String photos) {
        Photos = photos;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
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

    public Long getIdCompany() {
        return idCompany;
    }

    public void setIdCompany(Long idCompany) {
        this.idCompany = idCompany;
    }
}
