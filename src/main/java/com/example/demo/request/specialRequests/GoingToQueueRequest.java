package com.example.demo.request.specialRequests;

public class GoingToQueueRequest {

    private Long id;
    private Long idUser;
    private Long idPowerUnit;
    private Long idCar;
    private boolean fastCharge;
    private int givenPercentageForCharging;

    public GoingToQueueRequest()  {

    }

    public GoingToQueueRequest(Long id, Long idUser, Long idPowerUnit, Long idCar, boolean fastCharge, int givenPercentageForCharging) {
        this.id = id;
        this.idUser = idUser;
        this.idPowerUnit = idPowerUnit;
        this.idCar = idCar;
        this.fastCharge = fastCharge;
        this.givenPercentageForCharging = givenPercentageForCharging;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public Long getIdPowerUnit() {
        return idPowerUnit;
    }

    public void setIdPowerUnit(Long idPowerUnit) {
        this.idPowerUnit = idPowerUnit;
    }

    public Long getIdCar() {
        return idCar;
    }

    public void setIdCar(Long idCar) {
        this.idCar = idCar;
    }

    public boolean isFastCharge() {
        return fastCharge;
    }

    public void setFastCharge(boolean fastCharge) {
        this.fastCharge = fastCharge;
    }


    public int getGivenPercentageForCharging() {
        return givenPercentageForCharging;
    }

    public void setGivenPercentageForCharging(int givenPercentageForCharging) {
        this.givenPercentageForCharging = givenPercentageForCharging;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
