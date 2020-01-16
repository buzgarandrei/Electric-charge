package com.example.demo.repositories;

import com.example.demo.entities.ChargerequestsEntity;
import com.example.demo.request.ChargerequestRequest;
import com.example.demo.request.specialRequests.RequestWithIdOnly;
import com.example.demo.response.ChargerequestResponse;

import java.util.List;

public interface ChargerequestRepository {
    public List<ChargerequestsEntity> findAll();

    public void addChargerequest( ChargerequestRequest chargerequestRequest)throws Exception;

    public void updateChargerequest(ChargerequestRequest chargerequestRequest)throws Exception;

    public void deleteChargerequest(RequestWithIdOnly id)throws Exception;

    ChargerequestResponse getRequestById(RequestWithIdOnly request);
}
