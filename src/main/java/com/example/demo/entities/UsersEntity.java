package com.example.demo.entities;

import com.example.demo.entities.enums.RoleEnum;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
//-pk id int
//-username str
//-password str
//-name str
//-cars int[]
//-role int
@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"username", "password"})
            }
        )
public class UsersEntity {

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

    @Column(name = "username", unique = true)
    private String Username;

    @Column(name = "password")
    private String Password;

    @Column(name = "name")
    private String Name;

    @Column
    private String city;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JoinTable(name = "users_stations",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_station"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"id_station","id_user"}))
    List<StationsEntity> favourites = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "users_cars",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_car"),
            uniqueConstraints=@UniqueConstraint(columnNames = {"id_user", "id_car"}))
    private List<CarsEntity> carsEntityList = new ArrayList<>();

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    public UsersEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public List<StationsEntity> getFavourites() {
        return favourites;
    }

    public void setFavourites(List<StationsEntity> favourites) {
        this.favourites = favourites;
    }

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }

    public List<CarsEntity> getCarsEntityList() {
        return carsEntityList;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCarsEntityList(List<CarsEntity> carsEntityList) {
        this.carsEntityList = carsEntityList;
    }
}
