package com.example.demo.request.specialRequests;

public class AssignPowerUnitToStation {

    Long idStation;
    Long idPowerUnit;

    public AssignPowerUnitToStation() {
    }

    public AssignPowerUnitToStation(Long idStation, Long idPowerUnit) {
        this.idStation = idStation;
        this.idPowerUnit = idPowerUnit;
    }

    public Long getIdStation() {
        return idStation;
    }

    public void setIdStation(Long idStation) {
        this.idStation = idStation;
    }

    public Long getIdPowerUnit() {
        return idPowerUnit;
    }

    public void setIdPowerUnit(Long idPowerUnit) {
        this.idPowerUnit = idPowerUnit;
    }
}
