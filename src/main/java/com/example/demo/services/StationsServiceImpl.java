package com.example.demo.services;

import com.example.demo.entities.PowerUnitEntity;
import com.example.demo.entities.StationsEntity;
import com.example.demo.repositories.StationsRepository;
import com.example.demo.request.StationRequest;
import com.example.demo.request.specialRequests.CityRequest;
import com.example.demo.request.specialRequests.RequestWithIdOnly;
import com.example.demo.response.PowerUnitsResponse;
import com.example.demo.response.StateResponse;
import com.example.demo.response.StationResponse;
import com.example.demo.utils.LatLng;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StationsServiceImpl implements StationsService {

    @Autowired
    StationsRepository stationsRepository;

    @Override
    public List<StationResponse> findAll() {
        List<StationResponse> stationResponseList = new ArrayList<>();
        List<StationsEntity> stationsEntityList = stationsRepository.findAll();

        for (StationsEntity entity : stationsEntityList) {

            StationResponse stationResponse = new StationResponse();
            stationResponse.setAddress(entity.getAddress());
            stationResponse.setName(entity.getName());
            stationResponse.setPrice(entity.getPrice());
            stationResponse.setPhotos(entity.getPhotos());
            stationResponse.setCity(entity.getCity());
            LatLng latLng = new LatLng();
            latLng.setLng(entity.getLng());
            latLng.setLat(entity.getLat());
            stationResponse.setLatLng(latLng);
            stationResponse.setAccuracy(entity.getAccuracy());
            stationResponse.setIdCompany(entity.getCompany().getId());

            stationResponseList.add(stationResponse);
        }

        return stationResponseList;
    }

    @Override
    public StateResponse addStation(StationRequest stationRequest) throws Exception {

        StateResponse stateResponse = new StateResponse();
        if (stationsRepository.addStation(stationRequest).isSuccess())
            stateResponse.setSuccess(true);
        else stateResponse.setSuccess(false);
        return stateResponse;

    }

    @Override
    public StateResponse updateStation(StationRequest stationRequest) throws Exception {

        StateResponse stateResponse = new StateResponse();
        if(stationsRepository.updateStation(stationRequest).isSuccess())
            stateResponse.setSuccess(true);
        else stateResponse.setSuccess(false);
        return stateResponse;
    }

    @Override
    public void deleteStation(RequestWithIdOnly id) throws Exception {

        stationsRepository.deleteStation(id);
    }

    @Override
    public StationResponse getStationById(RequestWithIdOnly request) {
        return stationsRepository.getStationById(request);
    }

    @Override
    public List<StationResponse> getStationsOfACity(CityRequest request) throws Exception {
        return stationsRepository.getStationsOfACity(request);
    }

    @Override
    public List<PowerUnitsResponse> getPowerUnitsOfaStation(RequestWithIdOnly request) {

        List<PowerUnitsResponse> responseList = new ArrayList<>();
        List<PowerUnitEntity> entityList = stationsRepository.getPowerUnitsOfaStation(request);
        for (PowerUnitEntity entity : entityList) {
            PowerUnitsResponse response = new PowerUnitsResponse();
            response.setPower(entity.getPower());
            response.setName(entity.getDescription());
            response.setIdStation(entity.getStationEntity().getId());
            response.setAvailable(entity.isAvailable());
            response.setFastCharge(entity.getFastCharge());

            responseList.add(response);
        }
        return responseList;
    }

}
