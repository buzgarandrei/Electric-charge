package com.example.demo.response;

public class CarResponse {

    private Long id;
    private String model;
    private Long idCompany;
    private String autonomy;
    private String chargingTime;

    public CarResponse() {
    }

    public CarResponse(Long id, String model, Long idCompany, String autonomy, String chargingTime) {
        this.id = id;
        this.model = model;
        this.idCompany = idCompany;
        this.autonomy = autonomy;
        this.chargingTime = chargingTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Long getIdCompany() {
        return idCompany;
    }

    public void setIdCompany(Long idCompany) {
        this.idCompany = idCompany;
    }

    public String getAutonomy() {
        return autonomy;
    }

    public void setAutonomy(String autonomy) {
        this.autonomy = autonomy;
    }

    public String getChargingTime() {
        return chargingTime;
    }

    public void setChargingTime(String chargingTime) {
        this.chargingTime = chargingTime;
    }
}
