package com.example.demo.entities;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//Networks:
//-pk id int
//-name str
//-power float
@Entity
@Table(name = "power_unit")
public class PowerUnitEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "power")
    private float Power;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_station")
    private StationsEntity stationEntity;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_power_unit")
    private List<ChargingQueue> chargingQueueList = new ArrayList<>();

    @Column(name = "fast_charge")
    private boolean fastCharge;

    @Column(name = "is_available")
    private boolean isAvailable;

    public PowerUnitEntity() {
    }

    public PowerUnitEntity(String description, float power, StationsEntity stationEntity, List<ChargingQueue> chargingQueueList, boolean fastCharge, boolean isAvailable) {
        this.description = description;
        Power = power;
        this.stationEntity = stationEntity;
        this.chargingQueueList = chargingQueueList;
        this.fastCharge = fastCharge;
        this.isAvailable = isAvailable;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPower() {
        return Power;
    }

    public void setPower(float power) {
        Power = power;
    }

    public StationsEntity getStationEntity() {
        return stationEntity;
    }

    public void setStationEntity(StationsEntity stationEntity) {
        this.stationEntity = stationEntity;
    }

    public List<ChargingQueue> getChargingQueueList() {
        return chargingQueueList;
    }

    public void setChargingQueueList(List<ChargingQueue> chargingQueueList) {
        this.chargingQueueList = chargingQueueList;
    }

    public boolean getFastCharge() {
        return fastCharge;
    }

    public void setFastCharge(boolean fastCharge) {
        this.fastCharge = fastCharge;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
