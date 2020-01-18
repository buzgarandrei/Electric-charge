package com.example.demo.services;

import com.example.demo.entities.PowerUnitEntity;
import com.example.demo.repositories.PowerUnitsRepository;
import com.example.demo.request.PowerUnitsRequest;
import com.example.demo.request.specialRequests.*;
import com.example.demo.response.PowerUnitsResponse;
import com.example.demo.response.StateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PowerUnitsServiceImpl implements PowerUnitsService {

    @Autowired
    PowerUnitsRepository powerUnitsRepository;

    @Override
    public List<PowerUnitsResponse> findAll() {

        List<PowerUnitsResponse> powerUnitsResponseList = new ArrayList<>();
        List<PowerUnitEntity> powerUnitEntityList = powerUnitsRepository.findAll();

        for (PowerUnitEntity entity : powerUnitEntityList) {

            PowerUnitsResponse response = new PowerUnitsResponse();
            response.setId(entity.getId());
            response.setName(entity.getDescription());
            response.setPower(entity.getPower());
            response.setIdStation(entity.getStationEntity().getId());
            response.setFastCharge(entity.getFastCharge());
            response.setAvailable(entity.isAvailable());

            powerUnitsResponseList.add(response);
        }

        return powerUnitsResponseList;
    }

    @Override
    public StateResponse addPowerUnit(PowerUnitsRequest powerUnitsRequest) throws Exception {

        StateResponse stateResponse = new StateResponse();
        if (powerUnitsRepository.addPowerUnit(powerUnitsRequest).isSuccess())
            stateResponse.setSuccess(true);
        else stateResponse.setSuccess(false);
        return stateResponse;

    }

    @Override
    public StateResponse updatePowerUnit(PowerUnitsRequest powerUnitsRequest) throws Exception {

        StateResponse stateResponse = new StateResponse();

        if (powerUnitsRepository.updatePowerUnit(powerUnitsRequest).isSuccess())
            stateResponse.setSuccess(true);
        else stateResponse.setSuccess(false);
        return stateResponse;

    }

    @Override
    public void deletePowerUnit(RequestWithIdOnly id) throws Exception {

        powerUnitsRepository.deletePowerUnit(id);

    }

    @Override
    public PowerUnitsResponse getPowerUnitById(RequestWithIdOnly request) {

        return powerUnitsRepository.getPowerUnitById(request);
    }


    @Override
    public StateResponse doCheckIn(CheckInRequest request) throws ParseException {

        return powerUnitsRepository.doCheckIn(request);
    }

    @Override
    public Float doCheckOut(CheckOutRequest request) {

        return powerUnitsRepository.doCheckOut(request);
    }

    @Override
    public void deleteCheckOut(RequestWithIdOnly request) {

        powerUnitsRepository.deleteCheckOut(request);
    }
}
