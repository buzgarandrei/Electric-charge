package com.example.demo.controllers;

import com.example.demo.entities.StationsEntity;
import com.example.demo.entities.enums.RoleEnum;
import com.example.demo.request.LoginRequest;
import com.example.demo.request.UsersRequest;
import com.example.demo.request.specialRequests.Car2Request;
import com.example.demo.request.specialRequests.CityRequest;
import com.example.demo.request.specialRequests.RequestWith2IDs;
import com.example.demo.request.specialRequests.RequestWithIdOnly;
import com.example.demo.response.*;
import com.example.demo.services.AuthenticationService;
import com.example.demo.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UsersController {

    @Autowired
    UsersService usersService;

    @Autowired
    AuthenticationService authenticationService;

    /**
     * it logs the user and returns things about the user
     * @param httpServletRequest
     * @param loginRequest
     * @return
     * @throws Exception
     */
    @PostMapping("/login")
    public LoginResponse login(HttpServletRequest httpServletRequest, @RequestBody LoginRequest loginRequest) throws Exception {

        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        LoginResponse response = usersService.login(username,password);
        response.setRole(authenticationService.getUserRole(response.getId()).toString());

        if(response.getId() != null) {

            RoleEnum roleEnum = authenticationService.getUserRole(response.getId());
            String token = authenticationService.registerLogin(response.getId(),response.getUsername(),roleEnum);

            response.setToken(token);
            response.setRole(roleEnum.toString());
            response.setUsername(username);
        }
        else return null;

        return response;
    }

    /**
     * it returns the whole list of users from db
     * @param httpServletRequest
     * @return
     */
    @RequestMapping("/getUsersList")
    public List<UsersResponse> getUsersList(HttpServletRequest httpServletRequest) {

        boolean validated = authenticationService.validateTokenAndRole(httpServletRequest, RoleEnum.ADMIN);
        if(!validated) return null;

        List<UsersResponse> usersResponseList;
        usersResponseList = usersService.findAll();

        return usersResponseList;
    }

    /**
     * it returns a user with the given id
     * @param httpServletRequest
     * @param request
     * @return
     */

    @RequestMapping("/getUserById")
    public UsersResponse getUserById(HttpServletRequest httpServletRequest, @RequestBody RequestWithIdOnly request) {

        boolean validated = authenticationService.validateTokenAndRole(httpServletRequest, null);
        if(!validated) return null;

        UsersResponse userResponse;
        userResponse = usersService.findById(request);

        return userResponse;
    }

    /**
     * it will add a user in db , first you have to check if the given region is in db already, and then add it's id in the request
     * @param httpServletRequest
     * @param usersRequest
     * @return
     */
    @RequestMapping(path = "/addUser",method = RequestMethod.POST)
    public StateResponse addUser(HttpServletRequest httpServletRequest, @RequestBody UsersRequest usersRequest) {

        boolean validated = authenticationService.validateTokenAndRole(httpServletRequest, null);
        if(!validated) return null;

        StateResponse stateResponse = new StateResponse();
        try {
            usersService.addUser(usersRequest);
            stateResponse.setSuccess(true);
        }
        catch (Exception e) {
            e.printStackTrace();
            stateResponse.setSuccess(false);
        }
        return stateResponse;
    }

    /**
     * it will update a user in db
     * @param httpServletRequest
     * @param usersRequest
     * @return
     */
    @RequestMapping(path = "/updateUser",method = RequestMethod.POST)
    public StateResponse updateUser(HttpServletRequest httpServletRequest, @RequestBody UsersRequest usersRequest) {

        boolean validated = authenticationService.validateTokenAndRole(httpServletRequest, null);
        if(!validated) return null;

        StateResponse stateResponse = new StateResponse();
        try {
            usersService.updateUser(usersRequest);
            stateResponse.setSuccess(true);
        }
        catch (Exception e) {
            e.printStackTrace();
            stateResponse.setSuccess(false);
        }
        return stateResponse;
    }

    /**
     * it will delete a user with the given id
     * @param httpServletRequest
     * @param id
     * @return
     */
    @RequestMapping(path = "/deleteUser",method = RequestMethod.POST)
    public StateResponse deleteUser(HttpServletRequest httpServletRequest, @RequestBody RequestWithIdOnly id) {

        boolean validated = authenticationService.validateTokenAndRole(httpServletRequest, RoleEnum.ADMIN);
        if(!validated) return null;

        StateResponse stateResponse = new StateResponse();
        try {
            usersService.deleteUser(id);
            stateResponse.setSuccess(true);
        }
        catch (Exception e) {
            e.printStackTrace();
            stateResponse.setSuccess(false);
        }
        return stateResponse;
    }

    /**
     * it will add a station to the favourites list of a user
     * @param httpServletRequest
     * @param requestWith2IDs
     * @return
     */
    @RequestMapping(path = "/addFavourites", method = RequestMethod.POST)
    public StateResponse addFavourites(HttpServletRequest httpServletRequest, @RequestBody RequestWith2IDs requestWith2IDs) {

        boolean validated = authenticationService.validateTokenAndRole(httpServletRequest, null);
        if(!validated) return null;

        StateResponse stateResponse = new StateResponse();
        try {
            usersService.addFavourites(requestWith2IDs);
            stateResponse.setSuccess(true);
        }
        catch (Exception e) {
            e.printStackTrace();
            stateResponse.setSuccess(false);
        }
        return stateResponse;
    }

    /**
     * it will return the list of the stations that are favourites to a user
     * @param httpServletRequest
     * @param idOnly
     * @return
     */
    @RequestMapping(path = "/getFavourites", method = RequestMethod.GET)
    public List<StationResponse> getFavouritesList(HttpServletRequest httpServletRequest, @RequestBody RequestWithIdOnly idOnly) {

        boolean validated = authenticationService.validateTokenAndRole(httpServletRequest, null);
        if(!validated) return null;

        List<StationResponse> stationsResponseList = usersService.getFavourites(idOnly);

        return stationsResponseList;

    }

    /**
     * it will give the function Admin to a user
     * @param httpServletRequest
     * @param requestWithIdOnly
     * @return
     */

    @RequestMapping(path = "/makeAdmin", method = RequestMethod.POST)
    public StateResponse makeAdmin(HttpServletRequest httpServletRequest,@RequestBody RequestWithIdOnly requestWithIdOnly) {

        boolean validated = authenticationService.validateTokenAndRole(httpServletRequest, RoleEnum.ADMIN);
        if(!validated) return null;

        StateResponse stateResponse = new StateResponse();
        try {
            usersService.makeAdmin(requestWithIdOnly);
            stateResponse.setSuccess(true);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return stateResponse;
    }

    /**
     * it will give the function StationOwner to a user
     * @param httpServletRequest
     * @param requestWithIdOnly
     * @return
     */
    @RequestMapping(path = "/makeStationOwner", method = RequestMethod.POST)
    public StateResponse makeStationOwner(HttpServletRequest httpServletRequest,@RequestBody RequestWithIdOnly requestWithIdOnly) {

        boolean validated = authenticationService.validateTokenAndRole(httpServletRequest, RoleEnum.ADMIN);
        if(!validated) return null;

        StateResponse stateResponse = new StateResponse();

        try {
            usersService.makeStationOwner(requestWithIdOnly);
            stateResponse.setSuccess(true);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return stateResponse;
    }

    /**
     * it will give the function PremiumUser to a user
     * @param httpServletRequest
     * @param requestWithIdOnly
     * @return
     */
    @RequestMapping(path = "/makePremiumUser", method = RequestMethod.POST)
    public StateResponse makePremiumUser(HttpServletRequest httpServletRequest,@RequestBody RequestWithIdOnly requestWithIdOnly) {

        boolean validated = authenticationService.validateTokenAndRole(httpServletRequest, RoleEnum.ADMIN);
        if(!validated) return null;

        StateResponse stateResponse = new StateResponse();

        try {
            usersService.makePremiumUser(requestWithIdOnly);
            stateResponse.setSuccess(true);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return stateResponse;
    }

    @RequestMapping(value = "/addCarToUserList",method = RequestMethod.POST)
    public StateResponse addCarToUserList(HttpServletRequest httpServletRequest,@RequestBody Car2Request request) {

        boolean validated = authenticationService.validateTokenAndRole(httpServletRequest, RoleEnum.ADMIN);
        if(!validated) return null;

        StateResponse stateResponse = new StateResponse();

        try {
            usersService.addCarToUserList(request);
            stateResponse.setSuccess(true);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return stateResponse;
    }

    @RequestMapping(value = "/deleteCarFromUserList",method = RequestMethod.POST)
    public StateResponse deleteCarFromUserList(HttpServletRequest httpServletRequest,@RequestBody Car2Request request) {

        boolean validated = authenticationService.validateTokenAndRole(httpServletRequest, RoleEnum.ADMIN);
        if(!validated) return null;

        StateResponse stateResponse = new StateResponse();

        try {
            usersService.deleteCarFromUserList(request);
            stateResponse.setSuccess(true);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return stateResponse;
    }

    @RequestMapping(path = "/getCarListOfUser", method = RequestMethod.GET)
    public List<CarResponse> getCarListOfUser(HttpServletRequest httpServletRequest, @RequestBody RequestWithIdOnly idOnly) {

        boolean validated = authenticationService.validateTokenAndRole(httpServletRequest, RoleEnum.ADMIN);
        if(!validated) return null;

        List<CarResponse> carsResponseList = usersService.getCarListOfUser(idOnly);

        return carsResponseList;

    }

    @RequestMapping(path = "/getUsersOfARegion", method = RequestMethod.GET)
    public List<UsersResponse> getUsersOfARegion(HttpServletRequest httpServletRequest, @RequestBody CityRequest request) {

        boolean validated = authenticationService.validateTokenAndRole(httpServletRequest, RoleEnum.ADMIN);
        if(!validated) return null;

        List<UsersResponse> usersResponseList = usersService.getUsersOfARegion(request);

        return usersResponseList;

    }
}
