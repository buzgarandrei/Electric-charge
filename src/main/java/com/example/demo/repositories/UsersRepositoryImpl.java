package com.example.demo.repositories;

import com.example.demo.entities.*;
import com.example.demo.entities.enums.RoleEnum;
import com.example.demo.request.UsersRequest;
import com.example.demo.request.specialRequests.Car2Request;
import com.example.demo.request.specialRequests.CityRequest;
import com.example.demo.request.specialRequests.RequestWith2IDs;
import com.example.demo.request.specialRequests.RequestWithIdOnly;
import com.example.demo.response.CarResponse;
import com.example.demo.response.StateResponse;
import com.example.demo.response.StationResponse;
import com.example.demo.response.UsersResponse;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Repository
public class UsersRepositoryImpl implements UsersRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public List<UsersEntity> findAll() {

        Query query = entityManager.createQuery("select u from UsersEntity u", UsersEntity.class);
        List<UsersEntity> resultList = query.getResultList();

        return resultList;
    }

    @Override
    @Transactional
    public void addUser(UsersRequest usersRequest) throws Exception {

        UsersEntity usersEntity = new UsersEntity();

        usersEntity.setName(usersRequest.getName());
        usersEntity.setPassword(usersRequest.getPassword());
        usersEntity.setUsername(usersRequest.getUsername());
        usersEntity.setRole(RoleEnum.BASIC_USER);
        usersEntity.setCity(usersRequest.getCity());

        entityManager.persist(usersEntity);
    }

    @Override
    @Transactional
    public void updateUser(UsersRequest usersRequest) throws Exception {

        try {
            UsersEntity usersEntity = entityManager.find(UsersEntity.class, usersRequest.getId());

            if (usersRequest.getName() != null)
                usersEntity.setName(usersRequest.getName());
            if (usersRequest.getPassword() != null)
                usersEntity.setPassword(usersRequest.getPassword());
            String string = usersEntity.getUsername();
            usersEntity.setUsername(string);
            if(usersRequest.getCity()!= null) {
                usersEntity.setCity(usersRequest.getCity());
        }

            entityManager.merge(usersEntity);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("bad user id");
        }
    }

    @Override
    @Transactional
    public void deleteUser(RequestWithIdOnly id) throws Exception {
        try {
            UsersEntity entity = entityManager.find(UsersEntity.class, id.getId());
            entityManager.remove(entity);
        } catch (Exception e) {
            System.out.println("Problem deleting, bad id or smth");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Long login(String username, String password) throws Exception {

        Query query = entityManager.createQuery("select u.id from UsersEntity u " +
                "where u.Username = :username and u.Password = :password");

        query.setParameter("username", username);
        query.setParameter("password", password);

        return (Long) query.getSingleResult();
    }

    @Override
    @Transactional
    public UsersResponse findById(RequestWithIdOnly request) {

        UsersEntity usersEntity = entityManager.find(UsersEntity.class, request.getId());
        UsersResponse usersResponse = new UsersResponse();
        usersResponse.setId(usersEntity.getId());
        usersResponse.setUsername(usersEntity.getUsername());
        usersResponse.setRole(usersEntity.getRole().toString());
        usersResponse.setName(usersEntity.getName());

        return usersResponse;

    }

    @Override
    @Transactional
    public StateResponse addFavourites(RequestWith2IDs requestWith2IDs) {

        StateResponse stateResponse = new StateResponse();
        UsersEntity userEntity = entityManager.find(UsersEntity.class, requestWith2IDs.getIdUser());
        if(userEntity == null) {
            stateResponse.setSuccess(false);
            return stateResponse;
        }
        StationsEntity stationsEntity = entityManager.find(StationsEntity.class, requestWith2IDs.getIdStation());
        if(stationsEntity == null) {
            stateResponse.setSuccess(false);
            return stateResponse;
        }
        userEntity.getFavourites().add(stationsEntity);
        entityManager.merge(userEntity);
        stateResponse.setSuccess(true);
        return stateResponse;


    }

    @Override
    @Transactional
    public List<StationResponse> getFavourites(RequestWithIdOnly idOnly) {

        UsersEntity usersEntity = entityManager.find(UsersEntity.class, idOnly.getId());

        List<StationResponse> list = new ArrayList<>();

        List<StationsEntity> stationsEntityList = usersEntity.getFavourites();

        for (StationsEntity entity : stationsEntityList) {

            StationResponse response = new StationResponse();
            response.setId(entity.getId());
            response.setAddress(entity.getAddress());
            response.setName(entity.getName());
            response.setPhotos(entity.getPhotos());
            response.setPrice(entity.getPrice());

            list.add(response);
        }
        return list;
    }

    @Override
    @Transactional
    public void makeAdmin(RequestWithIdOnly requestWithIdOnly) throws Exception {

        UsersEntity userEntity = entityManager.find(UsersEntity.class, requestWithIdOnly.getId());
        userEntity.setRole(RoleEnum.ADMIN);

        entityManager.merge(userEntity);

    }

    @Override
    @Transactional
    public void makeStationOwner(RequestWithIdOnly requestWithIdOnly) throws Exception {

        UsersEntity userEntity = entityManager.find(UsersEntity.class, requestWithIdOnly.getId());
        userEntity.setRole(RoleEnum.STATION_OWNER);

        entityManager.merge(userEntity);
    }

    @Override
    @Transactional
    public void makePremiumUser(RequestWithIdOnly requestWithIdOnly) throws Exception {

        UsersEntity usersEntity = entityManager.find(UsersEntity.class, requestWithIdOnly.getId());
        usersEntity.setRole(RoleEnum.PREMIUM_USER);

        entityManager.merge(usersEntity);
    }

    @Override
    @Transactional
    public RoleEnum getUserRole(Long id) {

        Query query = entityManager.createQuery(" select user.role from UsersEntity user where user.id = :id ")
                                    .setParameter("id",id);

        List resultList = query.getResultList();
        if(resultList == null || resultList.isEmpty() || resultList.size() > 1) return null;

        RoleEnum roleEnum = (RoleEnum) resultList.get(0);
        return roleEnum;
    }

    @Override
    @Transactional
    public StateResponse addCarToUserList(Car2Request request) {

        StateResponse stateResponse = new StateResponse();
        CarsEntity carsEntity = entityManager.find(CarsEntity.class,request.getIdCar());
        if(carsEntity == null) {
            stateResponse.setSuccess(false);
            return stateResponse;
        }
        UsersEntity usersEntity = entityManager.find(UsersEntity.class,request.getIdUser());
        if(usersEntity == null) {
            stateResponse.setSuccess(false);
            return stateResponse;
        }
        usersEntity.getCarsEntityList().add(carsEntity);

        entityManager.persist(usersEntity);
        stateResponse.setSuccess(true);
        return stateResponse;
    }

    @Override
    @Transactional
    public StateResponse deleteCarFromUserList(Car2Request request) {

        StateResponse stateResponse = new StateResponse();
        CarsEntity carsEntity = entityManager.find(CarsEntity.class,request.getIdCar());
        if(carsEntity == null) {
            stateResponse.setSuccess(false);
            return stateResponse;
        }
        UsersEntity usersEntity = entityManager.find(UsersEntity.class,request.getIdUser());
        if(usersEntity == null) {
            stateResponse.setSuccess(false);
            return stateResponse;
        }
        Query query = entityManager.createQuery("select user.carsEntityList from UsersEntity user where user.id = :id",Collection.class)
                                    .setParameter("id", request.getIdUser());
        List<CarsEntity> carsList = query.getResultList();
        boolean found = false;
        for (CarsEntity entity : carsList) {
            if(entity.getId() == request.getIdCar()) {
                found = true;
                break;
            }
        }
        if(!found) {
            stateResponse.setSuccess(false);
            return stateResponse;
        }

        usersEntity.getCarsEntityList().remove(carsEntity.getId());

        entityManager.merge(usersEntity);
        stateResponse.setSuccess(true);
        return stateResponse;
    }

    @Override
    @Transactional
    public List<CarResponse> getCarListOfUser(RequestWithIdOnly idOnly) {

        Query query = entityManager.createQuery(" select user from UsersEntity user" +
                                                    " where user.id = :id ", UsersEntity.class);
        query.setParameter("id",idOnly.getId());

        List<CarsEntity> resultList = ((UsersEntity)query.getSingleResult()).getCarsEntityList();

        List<CarResponse> responseList = new ArrayList<>();

        for (CarsEntity carsEntity : resultList) {

            CarResponse carResponse = new CarResponse();

            carResponse.setId(carsEntity.getId());
            carResponse.setIdCompany(carsEntity.getIdCompany().getId());
            carResponse.setAutonomy(carsEntity.getAutonomy());
            carResponse.setChargingTime(carsEntity.getChargingTime());
            carResponse.setModel(carsEntity.getModel());

            responseList.add(carResponse);
        }
        return responseList;
    }

    @Override
    @Transactional
    public List<UsersResponse> getUsersOfARegion(CityRequest request) {

        Query query = entityManager.createQuery(" select users from UsersEntity users " +
                                                    "where users.city = :city ", UsersEntity.class);
        query.setParameter("city", request.getCity());

        List<UsersEntity> usersEntityList = query.getResultList();

        List<UsersResponse> responseList = new ArrayList<>();

        for (UsersEntity usersEntity : usersEntityList) {

            UsersResponse response = new UsersResponse();
            response.setCity(usersEntity.getCity());
            response.setName(usersEntity.getName());
            response.setUsername(usersEntity.getPassword());
            response.setRole(usersEntity.getRole().toString());
            response.setId(usersEntity.getId());

            responseList.add(response);

        }

        return responseList;

    }


}
