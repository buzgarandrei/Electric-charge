package com.example.demo.repositories;

import com.example.demo.entities.CompaniesEntity;
import com.example.demo.entities.StationsEntity;
import com.example.demo.request.StationRequest;
import com.example.demo.request.specialRequests.CityRequest;
import com.example.demo.request.specialRequests.RequestWithIdOnly;
import com.example.demo.response.StationResponse;
import com.example.demo.utils.LatLng;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StationsRepositoryImpl implements StationsRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public List<StationsEntity> findAll() {

        Query query = entityManager.createQuery("select s from StationsEntity s", StationsEntity.class);
        List<StationsEntity> resultList = query.getResultList();

        return resultList;
    }

    @Override
    @Transactional
    public void addStation(StationRequest stationRequest) throws Exception {
        StationsEntity stationsEntity = new StationsEntity();
        stationsEntity.setAddress(stationRequest.getAddress());
        stationsEntity.setName(stationRequest.getName());
        stationsEntity.setPrice(stationRequest.getPrice());
        stationsEntity.setCity(stationRequest.getCity());

        stationsEntity.setLat(stationRequest.getLatLng().getLat());
        stationsEntity.setLng(stationRequest.getLatLng().getLng());
        stationsEntity.setAccuracy(stationRequest.getAccuracy());
        stationsEntity.setCompany(entityManager.find(CompaniesEntity.class, stationRequest.getIdCompany()));

        entityManager.persist(stationsEntity);
    }

    @Override
    @Transactional
    public void updateStation(StationRequest stationRequest) throws Exception {

        StationsEntity stationsEntity = entityManager.find(StationsEntity.class, stationRequest.getId());

        if(stationRequest.getPhotos() != null)
            stationsEntity.setPhotos(stationRequest.getPhotos());
        if(stationRequest.getPrice() != 0)
            stationsEntity.setPrice(stationRequest.getPrice());
        if(stationRequest.getName() != null)
            stationsEntity.setName(stationRequest.getName());
        if(stationRequest.getAddress() != null)
            stationsEntity.setAddress(stationRequest.getAddress());
        if(stationRequest.getCity() != null)
            stationsEntity.setCity(stationRequest.getCity());

        //if(stationRequest.getAccuracy() != 0)
            //stationsEntity.setAccuracy(stationRequest.getAccuracy());

        if(stationRequest.getLatLng().getLat() != 0)
            stationsEntity.setLat(stationRequest.getLatLng().getLat());
        if(stationRequest.getLatLng().getLng() != 0)
            stationsEntity.setLng(stationRequest.getLatLng().getLng());
        if(stationRequest.getIdCompany() != null)
            stationsEntity.setCompany(entityManager.find(CompaniesEntity.class, stationRequest.getIdCompany()));
        entityManager.merge(stationsEntity);
    }

    @Override
    @Transactional
    public void deleteStation(RequestWithIdOnly id) throws Exception {
        try {
            StationsEntity entity = entityManager.find(StationsEntity.class, id.getId());
            entityManager.remove(entity);
        } catch (Exception e) {
            System.out.println("bad station id");
        }
    }

    @Override
    public StationResponse getStationById(RequestWithIdOnly request) {

        StationsEntity stationEntity = entityManager.find(StationsEntity.class, request.getId());
        StationResponse response = new StationResponse();
        response.setId(stationEntity.getId());
        response.setPhotos(stationEntity.getPhotos());
        response.setPrice(stationEntity.getPrice());
        response.setName(stationEntity.getName());
        response.setAddress(stationEntity.getAddress());
        response.setCity(stationEntity.getCity());

        LatLng latLng = new LatLng();
        latLng.setLat(stationEntity.getLat());
        latLng.setLng(stationEntity.getLng());

        response.setLatLng(latLng);
        response.setAccuracy(stationEntity.getAccuracy());
        response.setIdCompany(stationEntity.getCompany().getId());

        return response;

    }

    @Override
    @Transactional
    public List<StationResponse> getStationsOfACity(CityRequest request) throws Exception {

        Query query = entityManager.createQuery(" select stations from StationsEntity stations " +
                                                                     " where stations.city = :cityName ",StationsEntity.class)
                                    .setParameter("cityName",request.getCity());

        List<StationsEntity> resultList = query.getResultList();
        List<StationResponse> responseList = new ArrayList<>();

        for (StationsEntity entity: resultList) {

            StationResponse response = new StationResponse();
            response.setPrice(entity.getPrice());
            response.setCity(entity.getCity());
            response.setId(entity.getId());
            response.setPhotos(entity.getPhotos());
            response.setAddress(entity.getAddress());
            response.setName(entity.getName());
            response.setIdCompany(entity.getCompany().getId());

            LatLng latLng = new LatLng();
            latLng.setLng(entity.getLng());
            latLng.setLat(entity.getLat());

            response.setLatLng(latLng);
            response.setAccuracy(entity.getAccuracy());

            responseList.add(response);
        }

        return responseList;

    }
}
