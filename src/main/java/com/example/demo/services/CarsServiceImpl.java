package com.example.demo.services;

import com.example.demo.entities.CarsEntity;
import com.example.demo.repositories.CarsRepository;
import com.example.demo.request.CarRequest;
import com.example.demo.request.specialRequests.RequestWithIdOnly;
import com.example.demo.response.CarResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarsServiceImpl implements CarsService {

    @Autowired
    private CarsRepository carsRepository;

    @Override
    public List<CarResponse> findAll() {

        List<CarResponse> carResponseList = new ArrayList<>();
        List<CarsEntity> carsEntityList = carsRepository.getCarsList();
        for (CarsEntity carsEntity : carsEntityList ) {

            CarResponse carResponse = new CarResponse();

            carResponse.setModel(carsEntity.getModel());
            carResponse.setId(carsEntity.getId());
            carResponse.setChargingTime(carsEntity.getChargingTime());
            carResponse.setAutonomy(carsEntity.getAutonomy());

            try {
                carResponse.setIdCompany(carsEntity.getIdCompany().getId());
            }
            catch (NullPointerException e) {
                e.printStackTrace();
                System.out.println("bad id");
            }

            carResponseList.add(carResponse);
        }
        return carResponseList;
    }

    @Override
    public void addCar(CarRequest carRequest) throws Exception{

        carsRepository.addCar(carRequest);

    }

    @Override
    public void updateCar(CarRequest carRequest) throws Exception{

        carsRepository.updateCar(carRequest);

    }

    @Override
    public void deleteCar(RequestWithIdOnly id) throws Exception {

        carsRepository.deleteCar(id);
    }

    @Override
    public CarResponse getCarById(RequestWithIdOnly request) {

        return carsRepository.getCarById(request);
    }
}
