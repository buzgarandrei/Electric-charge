package com.example.demo.repositories;

import com.example.demo.entities.UsersEntity;
import com.example.demo.entities.enums.RoleEnum;
import com.example.demo.request.UsersRequest;
import com.example.demo.request.specialRequests.Car2Request;
import com.example.demo.request.specialRequests.CityRequest;
import com.example.demo.request.specialRequests.RequestWith2IDs;
import com.example.demo.request.specialRequests.RequestWithIdOnly;
import com.example.demo.response.CarResponse;
import com.example.demo.response.StationResponse;
import com.example.demo.response.UsersResponse;

import java.util.List;

public interface UsersRepository {
    public List<UsersEntity> findAll();

    public void addUser(UsersRequest usersRequest) throws Exception;
    public void updateUser(UsersRequest usersRequest) throws Exception;
    public void deleteUser(RequestWithIdOnly id) throws Exception;

    public Long login(String Username, String password) throws Exception;

    public UsersResponse findById(RequestWithIdOnly request);

    void addFavourites(RequestWith2IDs requestWith2IDs) throws Exception;

    List<StationResponse> getFavourites(RequestWithIdOnly idOnly);

    void makeAdmin(RequestWithIdOnly requestWithIdOnly) throws Exception;

    void makeStationOwner(RequestWithIdOnly requestWithIdOnly) throws Exception;

    void makePremiumUser(RequestWithIdOnly requestWithIdOnly) throws Exception;

    RoleEnum getUserRole(Long id);

    void addCarToUserList(Car2Request request);

    void deleteCarFromUserList(Car2Request request);

    List<CarResponse> getCarListOfUser(RequestWithIdOnly idOnly);

    List<UsersResponse> getUsersOfARegion(CityRequest request);
}
