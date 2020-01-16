package com.example.demo.repositories;

import com.example.demo.entities.CarsEntity;
import com.example.demo.request.CarRequest;
import com.example.demo.request.specialRequests.RequestWithIdOnly;
import com.example.demo.response.CarResponse;

import java.util.List;

public interface CarsRepository {

    public List<CarsEntity> getCarsList() ;

    public void addCar(CarRequest carRequest) throws Exception;

    public void updateCar(CarRequest carRequest) throws Exception;

    public void deleteCar(RequestWithIdOnly id) throws Exception;

    CarResponse getCarById(RequestWithIdOnly request);
}
