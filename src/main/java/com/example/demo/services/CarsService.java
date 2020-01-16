package com.example.demo.services;

import com.example.demo.request.CarRequest;
import com.example.demo.request.specialRequests.RequestWithIdOnly;
import com.example.demo.response.CarResponse;

import java.util.List;

public interface CarsService {
    public List<CarResponse> findAll();

    public void addCar(CarRequest carRequest) throws Exception;

    public void updateCar(CarRequest carRequest) throws Exception;

    public void deleteCar(RequestWithIdOnly id) throws Exception;

    CarResponse getCarById(RequestWithIdOnly request);
}
