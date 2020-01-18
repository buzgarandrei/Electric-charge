package com.example.demo.repositories;

import com.example.demo.entities.*;
import com.example.demo.request.PowerUnitsRequest;
import com.example.demo.request.specialRequests.*;
import com.example.demo.response.PowerUnitsResponse;
import com.example.demo.response.StateResponse;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.text.ParseException;
import java.util.*;

@Repository
public class PowerUnitsRepositoryImpl implements PowerUnitsRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public List<PowerUnitEntity> findAll() {

        Query query = entityManager.createQuery("select pu from PowerUnitEntity pu", PowerUnitEntity.class);
        List<PowerUnitEntity> resultList = query.getResultList();
        return resultList;
    }

    @Override
    @Transactional
    public StateResponse addPowerUnit(PowerUnitsRequest powerUnitsRequest) throws Exception {

        StateResponse stateResponse = new StateResponse();
        try {
            PowerUnitEntity entity = new PowerUnitEntity();
            entity.setDescription(powerUnitsRequest.getName());
            entity.setPower(powerUnitsRequest.getPower());

            StationsEntity stationsEntity = entityManager.find(StationsEntity.class, powerUnitsRequest.getStationId());
            if(stationsEntity == null) {
                stateResponse.setSuccess(false);
                return stateResponse;
            }
            entity.setStationEntity(stationsEntity);
            if(powerUnitsRequest.getFastCharge() != null)
                entity.setFastCharge(powerUnitsRequest.getFastCharge());
            if(powerUnitsRequest.getAvailable() != null)
                entity.setAvailable(powerUnitsRequest.getAvailable());

            entityManager.persist(entity);
            stateResponse.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            stateResponse.setSuccess(false);
            System.out.println("bad power unit id");
        }
        return stateResponse;
    }

    @Override
    @Transactional
    public StateResponse updatePowerUnit(PowerUnitsRequest powerUnitsRequest) throws Exception {

        StateResponse stateResponse = new StateResponse();
        try {

            PowerUnitEntity entity = entityManager.find(PowerUnitEntity.class, powerUnitsRequest.getId());

            if(powerUnitsRequest.getName() != null)
                entity.setDescription(powerUnitsRequest.getName());
            if(powerUnitsRequest.getPower() != 0)
                entity.setPower(powerUnitsRequest.getPower());

            if(powerUnitsRequest.getStationId() != null) {
                StationsEntity stationsEntity = entityManager.find(StationsEntity.class, powerUnitsRequest.getStationId());
                if(stationsEntity == null) {
                    stateResponse.setSuccess(false);
                    return stateResponse;
                }
                entity.setStationEntity(stationsEntity);
            }
            if(powerUnitsRequest.getFastCharge() != null) {
                entity.setFastCharge(powerUnitsRequest.getFastCharge());
            }
            if(powerUnitsRequest.getAvailable() != null) entity.setAvailable(powerUnitsRequest.getAvailable());

            entityManager.merge(entity);
            stateResponse.setSuccess(true);
        }
        catch (Exception e) {
            e.printStackTrace();
            stateResponse.setSuccess(false);
            System.out.println("Bad Power Unit id or other fields");
        }
        return stateResponse;

    }

    @Override
    @Transactional
    public void deletePowerUnit(RequestWithIdOnly id) throws Exception {

        try {
            PowerUnitEntity entity = entityManager.find(PowerUnitEntity.class, id.getId());
            entityManager.remove(entity);
        } catch (Exception e) {
            System.out.println("Bad Power Unit id");
            throw e;
        }
    }

    @Override
    public PowerUnitsResponse getPowerUnitById(RequestWithIdOnly request) {

        PowerUnitEntity entity = entityManager.find(PowerUnitEntity.class, request.getId());
        PowerUnitsResponse response = new PowerUnitsResponse();

        response.setId(entity.getId());
        response.setPower(entity.getPower());
        response.setName(entity.getDescription());
        response.setIdStation(entity.getStationEntity().getId());

        return response;
    }

    @Override
    @Transactional
    public StateResponse doCheckIn(CheckInRequest request) throws ParseException {

        StateResponse stateResponse = new StateResponse();
        PowerUnitEntity powerUnitEntity = entityManager.find(PowerUnitEntity.class, request.getIdPowerUnit());
        if(powerUnitEntity.isAvailable() == false) {
            stateResponse.setSuccess(false);
            return stateResponse;
        }


        Query query = entityManager.createQuery("select user.carsEntityList from UsersEntity user where user.id = :id", Collection.class)
                                    .setParameter("id", request.getIdUser());
        List<CarsEntity> carsEntityList = query.getResultList();
        boolean found = false;
        for (CarsEntity carsEntity : carsEntityList) {
            if(carsEntity.getId() == request.getIdCar())
                found = true;
        }
        if(!found) {
            stateResponse.setSuccess(false);
            return stateResponse;
        }

        ChargingQueue queue = new ChargingQueue();
        Date start = new Date();
        queue.setStartTime(start);
        queue.setUserEntity(entityManager.find(UsersEntity.class, request.getIdUser()));
        queue.setCar(entityManager.find(CarsEntity.class, request.getIdCar()));

        entityManager.persist(queue);

        powerUnitEntity.setAvailable(false);
        powerUnitEntity.getChargingQueueList().add(queue);
        entityManager.merge(powerUnitEntity);
        stateResponse.setSuccess(true);
        return stateResponse;
    }

    @Override
    @Transactional
    public Float doCheckOut(CheckOutRequest request) {

        PowerUnitEntity powerUnitEntity = entityManager.find(PowerUnitEntity.class, request.getIdPowerUnit());
        ChargingQueue queue = powerUnitEntity.getChargingQueueList().get(powerUnitEntity.getChargingQueueList().size() - 1);
        if(request.getIdCar() != queue.getCar().getId() ||
                request.getIdUser() != queue.getUserEntity().getId() ||
                powerUnitEntity.isAvailable())
            return null;

        Date endTime = new Date();
        queue.setEndTime(endTime);
        Long nrOfMinutes = ((queue.getEndTime().getTime()/60000) - (queue.getStartTime().getTime()/60000));
        Query query = entityManager.createQuery("select pw.stationEntity.price from PowerUnitEntity  pw where pw.id = :id", Float.class);
        query.setParameter("id", request.getIdPowerUnit());
        float price = (float) query.getSingleResult();

        float amount = ((float)nrOfMinutes / 60 ) * price;
        queue.setAmount(amount);
        powerUnitEntity.setAvailable(true);
        entityManager.merge(queue);
        entityManager.merge(powerUnitEntity);

        return (Float) amount;
    }

    @Override
    @Transactional
    public void deleteCheckOut(RequestWithIdOnly request) {

        ChargingQueue queue = entityManager.find(ChargingQueue.class, request.getId());

        entityManager.remove(queue);
    }

}
