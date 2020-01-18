package com.example.demo.repositories;

import com.example.demo.entities.CarsEntity;
import com.example.demo.entities.CompaniesEntity;
import com.example.demo.request.CarRequest;
import com.example.demo.request.specialRequests.RequestWithIdOnly;
import com.example.demo.response.CarResponse;
import com.example.demo.response.StateResponse;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class CarsRepositoryImpl implements CarsRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    public List<CarsEntity> getCarsList() {

        Query query = entityManager.createQuery("select c from CarsEntity c",CarsEntity.class);
        List<CarsEntity> resultList = query.getResultList();
        return resultList;
    }

    @Override
    @Transactional
    public StateResponse addCar(CarRequest carRequest) throws Exception {

        StateResponse stateResponse = new StateResponse();
        try {
            CarsEntity carsEntity = new CarsEntity();
            carsEntity.setModel(carRequest.getModel());
            carsEntity.setChargingTime(carRequest.getChargingTime());
            carsEntity.setAutonomy(carRequest.getAutonomy());
            try {
                CompaniesEntity company = entityManager.find(CompaniesEntity.class, carRequest.getIdCompany());
                if(company == null) {
                    stateResponse.setSuccess(false);
                    return stateResponse;
                }
                carsEntity.setIdCompany(company);
            }
            catch (NullPointerException e) {
                e.printStackTrace();
                System.out.println("Bad id");
            }

            entityManager.persist(carsEntity);
            stateResponse.setSuccess(true);
        }
        catch (Exception e) {
            e.printStackTrace();
            stateResponse.setSuccess(false);
            System.out.println("Bad car id");
            throw e;
        }
        return stateResponse;


    }

    @Override
    @Transactional
    public StateResponse updateCar(CarRequest carRequest) throws Exception {

        StateResponse stateResponse = new StateResponse();
        try {

            CarsEntity carsEntity = entityManager.find(CarsEntity.class, carRequest.getId());
            if(carRequest.getModel() != null) carsEntity.setModel(carRequest.getModel());
            if(carRequest.getIdCompany() != null) {
                CompaniesEntity companiesEntity = entityManager.find(CompaniesEntity.class, carRequest.getIdCompany());
                if(companiesEntity == null) {
                    stateResponse.setSuccess(false);
                    return stateResponse;
                }
                carsEntity.setIdCompany(companiesEntity);
            }
            if(carRequest.getAutonomy() != null) carsEntity.setAutonomy(carRequest.getAutonomy());
            if(carRequest.getChargingTime() != null) carsEntity.setChargingTime(carRequest.getChargingTime());

            entityManager.merge(carsEntity);
            stateResponse.setSuccess(true);
        }
        catch (Exception e) {
            e.printStackTrace();
            stateResponse.setSuccess(false);
            System.out.println("Bad Company id ");
            throw e;
        }
        return stateResponse;
    }

    @Override
    @Transactional
    public void deleteCar(RequestWithIdOnly id) throws Exception {

        try {
            CarsEntity carsEntity = entityManager.find(CarsEntity.class,id.getId());

            entityManager.remove(carsEntity);

        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Bad car id");
            throw e;
        }
    }

    @Override
    @Transactional
    public CarResponse getCarById(RequestWithIdOnly request) {

        CarsEntity entity = entityManager.find(CarsEntity.class,request.getId());
        CarResponse response = new CarResponse();
        response.setId(entity.getId());
        response.setModel(entity.getModel());
        response.setIdCompany(entity.getIdCompany().getId());
        response.setAutonomy(entity.getAutonomy());
        response.setChargingTime(entity.getChargingTime());

        return response;

    }
}
