package com.example.demo.repositories;

import com.example.demo.entities.*;
import com.example.demo.request.PowerUnitsRequest;
import com.example.demo.request.specialRequests.*;
import com.example.demo.response.PowerUnitsResponse;
import com.example.demo.response.StateResponse;
import com.example.demo.response.specialResponses.GoingToQueueResponse;
import com.example.demo.utils.Utils;
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
    public void addPowerUnit(PowerUnitsRequest powerUnitsRequest) throws Exception {

        try {
            PowerUnitEntity entity = new PowerUnitEntity();
            entity.setDescription(powerUnitsRequest.getName());
            entity.setPower(powerUnitsRequest.getPower());

            StationsEntity stationsEntity = entityManager.find(StationsEntity.class, powerUnitsRequest.getStationId());
            entity.setStationEntity(stationsEntity);
            entity.setFastCharge(powerUnitsRequest.getFastCharge());
            entity.setAvailable(powerUnitsRequest.getAvailable());

            entityManager.persist(entity);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("bad power unit id");
        }
    }

    @Override
    @Transactional
    public void updatePowerUnit(PowerUnitsRequest powerUnitsRequest) throws Exception {

        try {

            PowerUnitEntity entity = entityManager.find(PowerUnitEntity.class, powerUnitsRequest.getId());

            if(powerUnitsRequest.getName() != null)
                entity.setDescription(powerUnitsRequest.getName());
            if(powerUnitsRequest.getPower() != 0)
                entity.setPower(powerUnitsRequest.getPower());

            if(powerUnitsRequest.getStationId() != null) {
                StationsEntity stationsEntity = entityManager.find(StationsEntity.class, powerUnitsRequest.getStationId());
                entity.setStationEntity(stationsEntity);
            }
            if(powerUnitsRequest.getFastCharge() != null) {
                entity.setFastCharge(powerUnitsRequest.getFastCharge());
            }
            if(powerUnitsRequest.getAvailable() != null) entity.setAvailable(powerUnitsRequest.getAvailable());

            entityManager.merge(entity);
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Bad Power Unit id or other fields");
        }

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

    /*@Override
    @Transactional
    public GoingToQueueResponse makeAppointmentAtPowerUnit(GoingToQueueRequest goingToQueueRequest) throws Exception {

        PowerUnitEntity powerUnitEntity = entityManager.find(PowerUnitEntity.class, goingToQueueRequest.getIdPowerUnit());
        ChargingQueue chargingQueue = new ChargingQueue();
        chargingQueue.setFastCharge(goingToQueueRequest.isFastCharge());
        chargingQueue.setUserEntity(entityManager.find(UsersEntity.class, goingToQueueRequest.getIdUser()));

        Date start;
        if (powerUnitEntity.getChargingQueueList().size() == 0) {

            start = new Date();
            chargingQueue.setStartTime(start);
        } else {

            start = powerUnitEntity.getChargingQueueList().
                    get(powerUnitEntity.getChargingQueueList().size() - 1).
                    getEndTime();

            chargingQueue.setStartTime(start);
        }

        Query query = entityManager.createQuery("select unit.stationEntity.price from PowerUnitEntity unit" +
                                                    " where unit.id = :id", Float.class);
        query.setParameter("id", goingToQueueRequest.getIdPowerUnit());
        float number = (Float)query.getSingleResult();

        if (goingToQueueRequest.isFastCharge()) {

            UsersEntity userEntity = entityManager.find(UsersEntity.class,goingToQueueRequest.getIdUser());
            float minutes = entityManager.find(CarsEntity.class, goingToQueueRequest.getIdCar()).getMinutesForFastCharge();
            Date end = DateUtils.addMinutes(start, (int) (minutes * goingToQueueRequest.getGivenPercentageForCharging() / 100));
            end = DateUtils.addMinutes(end,10);
            chargingQueue.setEndTime(end);

            CarsEntity carsEntity = entityManager.find(CarsEntity.class, goingToQueueRequest.getIdCar());
            chargingQueue.setAmount((carsEntity.getMinutesForFastCharge() * goingToQueueRequest.getGivenPercentageForCharging() / 100
                    * carsEntity.getMinutesForNormalCharge() / carsEntity.getMinutesForFastCharge() ) /100 * number);
        }
        else {

            Date end = DateUtils.addMinutes(start, (int) (entityManager.find(CarsEntity.class, goingToQueueRequest.getIdCar()).getMinutesForNormalCharge() * goingToQueueRequest.getGivenPercentageForCharging() / 100));
            end = DateUtils.addMinutes(end,10);
            chargingQueue.setEndTime(end);

            UsersEntity userEntity = entityManager.find(UsersEntity.class,goingToQueueRequest.getIdUser());
            chargingQueue.setAmount((entityManager.find(CarsEntity.class, goingToQueueRequest.getIdCar()).getMinutesForNormalCharge() * goingToQueueRequest.getGivenPercentageForCharging() / 100) /100 * number);
        }

        entityManager.persist(chargingQueue);

        powerUnitEntity.getChargingQueueList().add(chargingQueue);
        entityManager.merge(powerUnitEntity);

        GoingToQueueResponse response = new GoingToQueueResponse();

        response.setId(chargingQueue.getId());
        response.setFastCharge(chargingQueue.isFastCharge());

        if (chargingQueue.getStartTime() == null) {

            Date date = new Date();
            response.setStartDate(Utils.DATE_FORMAT.format(date));
        }
        else {

            response.setStartDate(Utils.DATE_FORMAT.format(chargingQueue.getStartTime()));
        }

        response.setEndDate(Utils.DATE_FORMAT.format(chargingQueue.getEndTime()));
        response.setAmount(chargingQueue.getAmount());
        response.setIdUser(chargingQueue.getUserEntity().getId());

        return response;
        return null;
    }*/

    /*@Override
    public List<GoingToQueueResponse> getAppointmentsAtAPowerUnit(RequestWithIdOnly requestWithIdOnly) throws Exception {

        List<Collection> resultList = entityManager.createQuery(" select power.chargingQueueList from PowerUnitEntity power " +
                " where power.id = :id ", Collection.class)

                .setParameter("id", requestWithIdOnly.getId())
                .getResultList();

        List<GoingToQueueResponse> list = new ArrayList<>();

        for (int i = 0; i < resultList.size(); i++) {

            ChargingQueue entity = (ChargingQueue) resultList.get(i);

            GoingToQueueResponse response = new GoingToQueueResponse();
            response.setId(entity.getId());

            if (entity.getStartTime() == null) {
                Date date = new Date();
                response.setStartDate(Utils.DATE_FORMAT.format(date));
            }
            else {

                response.setStartDate(Utils.DATE_FORMAT.format(entity.getStartTime()));
            }

            response.setEndDate(Utils.DATE_FORMAT.format(entity.getEndTime()));
            response.setAmount(entity.getAmount());
            response.setIdUser(entity.getUserEntity().getId());


            list.add(response);


        }
        return list;

    }

    @Override
    @Transactional
    public GoingToQueueResponse getAppointmentById(RequestWithIdOnly requestWithIdOnly) throws Exception {

        ChargingQueue chargingQueue = entityManager.createQuery("select queue from ChargingQueue queue" +
                " where queue.id = :id ", ChargingQueue.class)

                .setParameter("id", requestWithIdOnly.getId())
                .getSingleResult();

        GoingToQueueResponse response = new GoingToQueueResponse();

        response.setId(chargingQueue.getId());

        if (chargingQueue.getStartTime() == null) {

            Date date = new Date();
            response.setStartDate(Utils.DATE_FORMAT.format(date));
        }
        else {

            response.setStartDate(Utils.DATE_FORMAT.format(chargingQueue.getStartTime()));
        }

        response.setEndDate(Utils.DATE_FORMAT.format(chargingQueue.getEndTime()));
        response.setAmount(chargingQueue.getAmount());
        response.setIdUser(chargingQueue.getUserEntity().getId());

        return response;
    }

    @Override
    public List<GoingToQueueResponse> getAllAppointments() throws Exception {

        Query query = entityManager.createQuery("select queues from ChargingQueue queues", ChargingQueue.class);
        List<ChargingQueue> resultList = query.getResultList();

        List<GoingToQueueResponse> list = new ArrayList<>();

        for (ChargingQueue chargingQueue : resultList) {

            GoingToQueueResponse response = new GoingToQueueResponse();

            response.setId(chargingQueue.getId());

            if (chargingQueue.getStartTime() == null) {

                Date date = new Date();
                response.setStartDate(Utils.DATE_FORMAT.format(date));
            }
            else {

                response.setStartDate(Utils.DATE_FORMAT.format(chargingQueue.getStartTime()));
            }

            response.setEndDate(Utils.DATE_FORMAT.format(chargingQueue.getEndTime()));
            response.setAmount(chargingQueue.getAmount());
            response.setIdUser(chargingQueue.getUserEntity().getId());

            list.add(response);
        }

        return list;
    }

    @Override
    @Transactional
    public void deleteAppointments(RequestWithIdOnly requestWithIdOnly) throws Exception {

        ChargingQueue chargingQueue = entityManager.find(ChargingQueue.class, requestWithIdOnly.getId());

        entityManager.remove(chargingQueue);
    }

    @Override
    @Transactional
    public void updateAppointment(UpdateToQueue request) throws Exception {

        ChargingQueue chargingQueue = entityManager.find(ChargingQueue.class, request.getId());

        try {
            if (request.getIdUserEntity() != 0) {

                UsersEntity usersEntity = entityManager.find(UsersEntity.class, request.getIdUserEntity());
                chargingQueue.setUserEntity(usersEntity);
            }

            if (request.getAmount() != 0) {

                chargingQueue.setAmount(request.getAmount());
            }

            if (request.getStartTime() != null) {

                chargingQueue.setStartTime(Utils.DATE_FORMAT.parse(request.getStartTime()));
            }

            if (request.getEndTime() != null) {

                chargingQueue.setEndTime(Utils.DATE_FORMAT.parse(request.getEndTime()));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        entityManager.merge(chargingQueue);

    }*/

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
