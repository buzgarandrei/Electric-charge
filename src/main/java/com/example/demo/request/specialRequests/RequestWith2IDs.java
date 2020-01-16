package com.example.demo.request.specialRequests;

//Good for many-to-many entities

public class RequestWith2IDs {

    private Long idUser;

    private Long idStation;

    public RequestWith2IDs() {
    }

    public RequestWith2IDs(Long idUser, Long idStation) {
        this.idUser = idUser;
        this.idStation = idStation;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public Long getIdStation() {
        return idStation;
    }

    public void setIdStation(Long idStation) {
        this.idStation = idStation;
    }
}
