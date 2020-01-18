package com.example.demo.services;

import com.example.demo.request.PowerUnitsRequest;
import com.example.demo.request.specialRequests.*;
import com.example.demo.response.PowerUnitsResponse;
import com.example.demo.response.StateResponse;

import java.text.ParseException;
import java.util.List;

public interface PowerUnitsService {
    public List<PowerUnitsResponse> findAll();

    public StateResponse addPowerUnit(PowerUnitsRequest powerUnitsRequest)throws Exception;

    public StateResponse updatePowerUnit(PowerUnitsRequest powerUnitsRequest)throws Exception;

    public void deletePowerUnit(RequestWithIdOnly id)throws Exception;

    PowerUnitsResponse getPowerUnitById(RequestWithIdOnly request);

    StateResponse doCheckIn(CheckInRequest request) throws ParseException;

    Float doCheckOut(CheckOutRequest request);

    void deleteCheckOut(RequestWithIdOnly request);
}
