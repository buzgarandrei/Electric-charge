package com.example.demo.repositories;

import com.example.demo.entities.StationsEntity;
import com.example.demo.request.RegionRequest;
import com.example.demo.request.StationRequest;
import com.example.demo.request.specialRequests.AssignPowerUnitToStation;
import com.example.demo.request.specialRequests.CityRequest;
import com.example.demo.request.specialRequests.RequestWithIdOnly;
import com.example.demo.response.StationResponse;

import java.util.List;

public interface StationsRepository {
    public List<StationsEntity> findAll();

    public void addStation(StationRequest stationRequest) throws Exception;

    public void updateStation(StationRequest stationRequest) throws Exception;

    public void deleteStation(RequestWithIdOnly stationRequest) throws Exception;

    public void addPowerUnitToStation(AssignPowerUnitToStation assignPowerUnitToStation) throws Exception;

    StationResponse getStationById(RequestWithIdOnly request);

    List<StationResponse> getStationsOfACity(CityRequest request) throws Exception;
}
