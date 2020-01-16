package com.example.demo.request.specialRequests;

import com.example.demo.entities.UsersEntity;

import javax.persistence.*;
import java.util.Date;

public class UpdateToQueue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String startTime;
    private String endTime;
    private Long idPowerUnitEntity;
    private float amount;
    private boolean fastCharge;
    private Long idUserEntity;

    public UpdateToQueue() {
    }

    public UpdateToQueue(String startTime, String endTime, Long idPowerUnitEntity, float amount, boolean fastCharge, Long idUserEntity) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.idPowerUnitEntity = idPowerUnitEntity;
        this.amount = amount;
        this.fastCharge = fastCharge;
        this.idUserEntity = idUserEntity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Long getIdPowerUnitEntity() {
        return idPowerUnitEntity;
    }

    public void setIdPowerUnitEntity(Long idPowerUnitEntity) {
        this.idPowerUnitEntity = idPowerUnitEntity;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public boolean isFastCharge() {
        return fastCharge;
    }

    public void setFastCharge(boolean fastCharge) {
        this.fastCharge = fastCharge;
    }

    public Long getIdUserEntity() {
        return idUserEntity;
    }

    public void setIdUserEntity(Long idUserEntity) {
        this.idUserEntity = idUserEntity;
    }
}
