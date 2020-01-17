package com.example.demo.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


//<editor-fold desc="Cars:
//-pk id int
//-model str
//-company id">
//</editor-fold>
@Entity
@Table(name = "cars")
public class CarsEntity {

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

    @Column
    private String model;

    @ManyToOne(fetch = FetchType.LAZY,cascade =  {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name = "id_company")
    private CompaniesEntity idCompany ;

    @Column
    private String autonomy;

    @Column(name = "charging_time")
    private String chargingTime;

    @ManyToMany(mappedBy = "carsEntityList")
    private List<UsersEntity> usersEntityList = new ArrayList<>();

    public CarsEntity() {
    }

    public CarsEntity(String model, CompaniesEntity idCompany, String autonomy, String chargingTime) {
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

    public CompaniesEntity getIdCompany() {
        return idCompany;
    }

    public void setIdCompany(CompaniesEntity idCompany) {
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
