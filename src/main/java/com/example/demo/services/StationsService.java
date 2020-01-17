package com.example.demo.services;

import com.example.demo.request.StationRequest;
import com.example.demo.request.specialRequests.CityRequest;
import com.example.demo.request.specialRequests.RequestWithIdOnly;
import com.example.demo.response.StationResponse;

import java.util.List;

public interface StationsService {

    public List<StationResponse> findAll();

    public void addStation(StationRequest stationRequest)throws Exception;

    public void updateStation(StationRequest stationRequest)throws Exception;

    public void deleteStation(RequestWithIdOnly id)throws Exception;

    StationResponse getStationById(RequestWithIdOnly request);

    List<StationResponse> getStationsOfACity(CityRequest request  ) throws Exception;
}
