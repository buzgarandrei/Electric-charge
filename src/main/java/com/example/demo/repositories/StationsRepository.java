package com.example.demo.repositories;

import com.example.demo.entities.PowerUnitEntity;
import com.example.demo.entities.StationsEntity;
import com.example.demo.request.StationRequest;
import com.example.demo.request.specialRequests.CityRequest;
import com.example.demo.request.specialRequests.RequestWithIdOnly;
import com.example.demo.response.StateResponse;
import com.example.demo.response.StationResponse;

import java.util.List;

public interface StationsRepository {
    public List<StationsEntity> findAll();

    public StateResponse addStation(StationRequest stationRequest) throws Exception;

    public void updateStation(StationRequest stationRequest) throws Exception;

    public StateResponse deleteStation(RequestWithIdOnly stationRequest) throws Exception;

    StationResponse getStationById(RequestWithIdOnly request);

    List<StationResponse> getStationsOfACity(CityRequest request) throws Exception;

    List<PowerUnitEntity> getPowerUnitsOfAStation(RequestWithIdOnly request);
}
