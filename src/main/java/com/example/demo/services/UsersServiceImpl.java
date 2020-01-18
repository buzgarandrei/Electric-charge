package com.example.demo.services;

import com.example.demo.entities.UsersEntity;
import com.example.demo.repositories.UsersRepository;
import com.example.demo.request.UsersRequest;
import com.example.demo.request.specialRequests.Car2Request;
import com.example.demo.request.specialRequests.CityRequest;
import com.example.demo.request.specialRequests.RequestWith2IDs;
import com.example.demo.request.specialRequests.RequestWithIdOnly;
import com.example.demo.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    UsersRepository usersRepository;

    @Override
    @Transactional
    public List<UsersResponse> findAll() {

        List<UsersResponse> usersResponseList = new ArrayList<>();
        List<UsersEntity> usersEntityList = usersRepository.findAll();

        for (UsersEntity userEntity : usersEntityList) {

            UsersResponse usersResponse = new UsersResponse();
            usersResponse.setName(userEntity.getName());
            usersResponse.setRole(userEntity.getRole().toString());
            usersResponse.setUsername(userEntity.getUsername());
            usersResponse.setCity(userEntity.getCity());
            try {
                usersResponse.setId(userEntity.getId());

            } catch (NullPointerException e) {
                e.printStackTrace();
                System.out.println("Bad id");
            }
            usersResponseList.add(usersResponse);
        }

        return usersResponseList;
    }

    @Override
    public void addUser(UsersRequest usersRequest) throws Exception {

        usersRepository.addUser(usersRequest);
    }

    @Override
    public void updateUser(UsersRequest usersRequest) throws Exception {

        usersRepository.updateUser(usersRequest);
    }

    @Override
    public void deleteUser(RequestWithIdOnly id) throws Exception {

        usersRepository.deleteUser(id);
    }

    @Override
    public LoginResponse login(String Username, String password) throws Exception {

        Long id = usersRepository.login(Username, password);
        LoginResponse loginResponse = new LoginResponse();

        if (id != null) {
            loginResponse.setSuccess(true);
            loginResponse.setId(id);
            loginResponse.setToken(UUID.randomUUID().toString().replace("-",""));
        }

        return loginResponse;
    }

    @Override
    public UsersResponse findById(RequestWithIdOnly request) {

        return usersRepository.findById(request);
    }

    @Override
    public StateResponse addFavourites(RequestWith2IDs requestWith2IDs) throws Exception {

        StateResponse stateResponse = new StateResponse();
        if(usersRepository.addFavourites(requestWith2IDs).isSuccess())
            stateResponse.setSuccess(true);
        else stateResponse.setSuccess(false);
        return stateResponse;
    }

    @Override
    public List<StationResponse> getFavourites(RequestWithIdOnly idOnly) {

        return usersRepository.getFavourites(idOnly);
    }

    @Override
    public void makeAdmin(RequestWithIdOnly requestWithIdOnly) throws Exception {

        usersRepository.makeAdmin(requestWithIdOnly);
    }

    @Override
    public void makeStationOwner(RequestWithIdOnly requestWithIdOnly) throws Exception {

        usersRepository.makeStationOwner(requestWithIdOnly);
    }

    @Override
    public void makePremiumUser(RequestWithIdOnly requestWithIdOnly) throws Exception {

        usersRepository.makePremiumUser(requestWithIdOnly);
    }

    @Override
    public StateResponse addCarToUserList(Car2Request request) {

        StateResponse stateResponse = new StateResponse();
        if(usersRepository.addCarToUserList(request).isSuccess())
            stateResponse.setSuccess(true);
        else stateResponse.setSuccess(false);
        return stateResponse;
    }

    @Override
    public StateResponse deleteCarFromUserList(Car2Request request) {

        StateResponse stateResponse = new StateResponse();
        if(usersRepository.deleteCarFromUserList(request).isSuccess())
            stateResponse.setSuccess(true);
        else stateResponse.setSuccess(false);
        return stateResponse;
    }

    @Override
    public List<CarResponse> getCarListOfUser(RequestWithIdOnly idOnly) {

        return usersRepository.getCarListOfUser(idOnly);
    }

    @Override
    public List<UsersResponse> getUsersOfARegion(CityRequest request) {

        return usersRepository.getUsersOfARegion(request);
    }
}
