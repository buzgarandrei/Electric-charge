package com.example.demo.repositories;

import com.example.demo.entities.CarsEntity;
import com.example.demo.request.CarRequest;
import com.example.demo.request.specialRequests.RequestWithIdOnly;
import com.example.demo.response.CarResponse;
import com.example.demo.response.StateResponse;

import java.util.List;

public interface CarsRepository {

    public List<CarsEntity> getCarsList() ;

    public StateResponse addCar(CarRequest carRequest) throws Exception;

    public StateResponse updateCar(CarRequest carRequest) throws Exception;

    public StateResponse deleteCar(RequestWithIdOnly id) throws Exception;

    CarResponse getCarById(RequestWithIdOnly request);
}
