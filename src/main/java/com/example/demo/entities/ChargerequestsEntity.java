package com.example.demo.entities;

import com.example.demo.entities.enums.ReadEnum;

import javax.persistence.*;

//-pk id int
//-user1 int
//-user2 int
//-message str
//-type int ##0 - message, 1 - request
@Entity
@Table(name = "requests", uniqueConstraints = @UniqueConstraint(columnNames = {"id_user1","id_user2"}))
public class ChargerequestsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user1")
    private UsersEntity usersEntity1;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user2")
    private UsersEntity userEntity2;

    @Column(name = "message")
    private String message;

    @Column(name = "read_or_not")
    private ReadEnum readOrNot;

    public ChargerequestsEntity() {
    }

    public ChargerequestsEntity(UsersEntity usersEntity1, UsersEntity userEntity2, String message, ReadEnum type) {
        this.usersEntity1 = usersEntity1;
        this.userEntity2 = userEntity2;
        this.message = message;
        this.readOrNot = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UsersEntity getUsersEntity1() {
        return usersEntity1;
    }

    public void setUsersEntity1(UsersEntity usersEntity1) {
        this.usersEntity1 = usersEntity1;
    }

    public UsersEntity getUserEntity2() {
        return userEntity2;
    }

    public void setUserEntity2(UsersEntity userEntity2) {
        this.userEntity2 = userEntity2;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ReadEnum getReadOrNot() {
        return readOrNot;
    }

    public void setReadOrNot(ReadEnum readOrNot) {
        this.readOrNot = readOrNot;
    }
}
