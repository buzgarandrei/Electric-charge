package com.example.demo.request.specialRequests;

import java.io.Serializable;

public class RequestWithIdOnly implements Serializable {

    Long id;

    public RequestWithIdOnly() {
    }

    public RequestWithIdOnly(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
