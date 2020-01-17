package com.example.demo.request;

public class  PowerUnitsRequest {

    private Long id;
    private String name;
    private float Power;
    private Long stationId;
    private Boolean fastCharge;
    private Boolean isAvailable;

    public PowerUnitsRequest() {
    }

    public PowerUnitsRequest(Long id, String name, float power, Long stationId, Boolean fastCharge, Boolean isAvailable) {
        this.id = id;
        this.name = name;
        this.Power = power;
        this.stationId = stationId;
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

    public Long getStationId() {
        return stationId;
    }

    public void setStationId(Long stationId) {
        this.stationId = stationId;
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
