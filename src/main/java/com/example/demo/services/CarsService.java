package com.example.demo.services;

import com.example.demo.request.CarRequest;
import com.example.demo.request.specialRequests.RequestWithIdOnly;
import com.example.demo.response.CarResponse;
import com.example.demo.response.StateResponse;

import java.util.List;

public interface CarsService {
    public List<CarResponse> findAll();

    public StateResponse addCar(CarRequest carRequest) throws Exception;

    public StateResponse updateCar(CarRequest carRequest) throws Exception;

    public void deleteCar(RequestWithIdOnly id) throws Exception;

    CarResponse getCarById(RequestWithIdOnly request);
}
