package com.example.demo.response.specialResponses;

public class GoingToQueueResponse {

    private Long id;
    private float amount;
    private String startDate;
    private String endDate;
    private boolean fastCharge;
    private Long idUser;

    public GoingToQueueResponse() {
    }

    public GoingToQueueResponse(Long id, float amount, String startDate, String endDate, boolean fastCharge, Long idUser) {
        this.id = id;
        this.amount = amount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.fastCharge = fastCharge;
        this.idUser = idUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public boolean isFastCharge() {
        return fastCharge;
    }

    public void setFastCharge(boolean fastCharge) {
        this.fastCharge = fastCharge;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }
}
