package com.example.demo.response;

public class PowerUnitsResponse {

    private Long id;
    private String name;
    private float Power;
    private Long idStation;
    private Boolean fastCharge;
    private Boolean isAvailable;

    public PowerUnitsResponse() {
    }

    public PowerUnitsResponse(Long id, String name, float power, Long idStation, Boolean fastCharge, Boolean isAvailable) {
        this.id = id;
        this.name = name;
        Power = power;
        this.idStation = idStation;
        this.fastCharge = fastCharge;
        this.isAvailable = isAvailable;
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

    public float getPower() {
        return Power;
    }

    public void setPower(float power) {
        Power = power;
    }

    public Long getIdStation() {
        return idStation;
    }

    public void setIdStation(Long idStation) {
        this.idStation = idStation;
    }

    public Boolean getFastCharge() {
        return fastCharge;
    }

    public void setFastCharge(Boolean fastCharge) {
        this.fastCharge = fastCharge;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }
}
