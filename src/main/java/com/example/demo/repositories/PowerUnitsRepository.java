package com.example.demo.repositories;

import com.example.demo.entities.PowerUnitEntity;
import com.example.demo.request.PowerUnitsRequest;
import com.example.demo.request.specialRequests.*;
import com.example.demo.response.PowerUnitsResponse;
import com.example.demo.response.StateResponse;
import com.example.demo.response.specialResponses.GoingToQueueResponse;

import java.text.ParseException;
import java.util.List;

public interface PowerUnitsRepository {
    public List<PowerUnitEntity> findAll();

    public void addPowerUnit(PowerUnitsRequest powerUnitsRequest)throws Exception;

    public void updatePowerUnit(PowerUnitsRequest powerUnitsRequest)throws Exception;

    public void deletePowerUnit(RequestWithIdOnly id)throws Exception;

    PowerUnitsResponse getPowerUnitById(RequestWithIdOnly request);

    StateResponse doCheckIn(CheckInRequest request) throws ParseException;

    Float doCheckOut(CheckOutRequest request);

    void deleteCheckOut(RequestWithIdOnly request);
}
