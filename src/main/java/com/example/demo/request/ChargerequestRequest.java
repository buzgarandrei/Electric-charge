package com.example.demo.request;

import com.example.demo.entities.enums.ReadEnum;

public class ChargerequestRequest {

    private Long id;
    private Long idUserEntity;
    private Long idUserEntity2;
    private String message;
    private ReadEnum readOrNot;

    public ChargerequestRequest() {
    }

    public ChargerequestRequest(Long id, Long idUserEntity, Long idUserEntity2, String message, ReadEnum readOrNot) {
        this.id = id;
        this.idUserEntity = idUserEntity;
        this.idUserEntity2 = idUserEntity2;
        this.message = message;
        this.readOrNot = readOrNot;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdUserEntity() {
        return idUserEntity;
    }

    public void setIdUserEntity(Long idUserEntity) {
        this.idUserEntity = idUserEntity;
    }

    public Long getIdUserEntity2() {
        return idUserEntity2;
    }

    public void setIdUserEntity2(Long idUserEntity2) {
        this.idUserEntity2 = idUserEntity2;
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
