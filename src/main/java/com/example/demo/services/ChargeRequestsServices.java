package com.example.demo.services;

import com.example.demo.request.ChargerequestRequest;
import com.example.demo.request.specialRequests.RequestWithIdOnly;
import com.example.demo.response.ChargerequestResponse;

import java.util.List;

public interface ChargeRequestsServices {
    public List<ChargerequestResponse> findAll();

    public void addChargerequest(ChargerequestRequest chargerequestRequest) throws Exception;

    public void updateChargerequest(ChargerequestRequest chargerequestRequest) throws Exception;

    public void deleteChargerequest(RequestWithIdOnly id) throws Exception;

    public ChargerequestResponse getRequestById(RequestWithIdOnly request);
}
