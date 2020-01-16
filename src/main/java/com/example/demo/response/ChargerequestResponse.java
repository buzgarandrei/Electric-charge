package com.example.demo.response;

import com.example.demo.entities.enums.ReadEnum;

public class ChargerequestResponse {

    private Long id;
    private Long idUserEntity1;
    private Long idUserEntity2;
    private String message;
    private ReadEnum readOrNot;

    public ChargerequestResponse() {
    }

    public ChargerequestResponse(Long id, Long idUserEntity1, Long idUserEntity2, String message, ReadEnum readOrNot) {
        this.id = id;
        this.idUserEntity1 = idUserEntity1;
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

    public Long getIdUserEntity1() {
        return idUserEntity1;
    }

    public void setIdUserEntity1(Long idUserEntity1) {
        this.idUserEntity1 = idUserEntity1;
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
