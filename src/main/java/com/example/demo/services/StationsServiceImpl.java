package com.example.demo.services;

import com.example.demo.entities.StationsEntity;
import com.example.demo.repositories.StationsRepository;
import com.example.demo.request.RegionRequest;
import com.example.demo.request.StationRequest;
import com.example.demo.request.specialRequests.AssignPowerUnitToStation;
import com.example.demo.request.specialRequests.CityRequest;
import com.example.demo.request.specialRequests.RequestWithIdOnly;
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
            LatLng latLng =new LatLng();
            latLng.setLng(entity.getLng());
            latLng.setLat(entity.getLat());
            stationResponse.setLatLng(latLng);
            stationResponse.setAccuracy(entity.getAccuracy());
            //stationResponse.setIdCompany(entity.getCompany().getId());

            stationResponseList.add(stationResponse);
        }

        return stationResponseList;
    }

    @Override
    public void addStation(StationRequest stationRequest) throws Exception {

        stationsRepository.addStation(stationRequest);
    }

    @Override
    public void updateStation(StationRequest stationRequest) throws Exception {

        stationsRepository.updateStation(stationRequest);
    }

    @Override
    public void deleteStation(RequestWithIdOnly id) throws Exception {

        stationsRepository.deleteStation(id);
    }

    @Override
    public void addPowerUnitToStation(AssignPowerUnitToStation assignPowerUnitToStation) throws Exception {

        stationsRepository.addPowerUnitToStation(assignPowerUnitToStation);
    }

    @Override
    public StationResponse getStationById(RequestWithIdOnly request) {
        return stationsRepository.getStationById(request);
    }

    @Override
    public List<StationResponse> getStationsOfACity(CityRequest request) throws Exception {
        return stationsRepository.getStationsOfACity(request);
    }

}
