package com.example.demo.controllers;

import com.example.demo.entities.enums.RoleEnum;
import com.example.demo.request.RegionRequest;
import com.example.demo.request.StationRequest;
import com.example.demo.request.specialRequests.AssignPowerUnitToStation;
import com.example.demo.request.specialRequests.CityRequest;
import com.example.demo.request.specialRequests.RequestWithIdOnly;
import com.example.demo.response.StateResponse;
import com.example.demo.response.StationResponse;
import com.example.demo.services.AuthenticationService;
import com.example.demo.services.StationsService;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class StationsController {

    @Autowired
    StationsService stationsService;

    @Autowired
    AuthenticationService authenticationService;

    /**
     * it will bring the list of all stations from db
     * @param httpServletRequest
     * @return stationsService.findAll()
     */
    @RequestMapping("/getStationsList")
    public List<StationResponse> getStationList(HttpServletRequest httpServletRequest) {

        boolean validated = authenticationService.validateTokenAndRole(httpServletRequest, RoleEnum.ADMIN);
        if(!validated) return null;

        return stationsService.findAll();
    }

    /**
     * it will get the station with the specified id from db
     * @param httpServletRequest
     * @param request
     * @return
     */
    @RequestMapping("/getStationById")
    public StationResponse getStationById(HttpServletRequest httpServletRequest, @RequestBody RequestWithIdOnly request) {

        boolean validated = authenticationService.validateTokenAndRole(httpServletRequest, RoleEnum.ADMIN);
        if(!validated) return null;

        StationResponse response = stationsService.getStationById(request);

        return response;
    }

    /**
     * it will bring a list of stations from a given city
     * @param httpServletRequest
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/getStationsOfACity")
    public List<StationResponse> getStationsOfACity(HttpServletRequest httpServletRequest, @RequestBody CityRequest request) throws Exception {

        boolean validated = authenticationService.validateTokenAndRole(httpServletRequest, null);
        if(!validated) return null;

        return stationsService.getStationsOfACity(request);
    }

    /**
     * it will add a station in db
     * @param httpServletRequest
     * @param stationRequest
     * @return
     */
    @RequestMapping(path = "/addStation",method = RequestMethod.POST)
    public StateResponse addStation(HttpServletRequest httpServletRequest, @RequestBody StationRequest stationRequest) {

        boolean validated = authenticationService.validateTokenAndRole(httpServletRequest, RoleEnum.ADMIN);
        if(!validated) return null;

        StateResponse stateResponse = new StateResponse();
        try {
            stationsService.addStation(stationRequest);
            stateResponse.setSuccess(true);
        }
        catch (Exception e) {
            e.printStackTrace();
            stateResponse.setSuccess(false);
        }
        return stateResponse;
    }

    /**
     * it will update a station
     * @param httpServletRequest
     * @param stationRequest
     * @return
     */
    @RequestMapping(path = "/updateStation",method = RequestMethod.POST)
    public StateResponse updateStation(HttpServletRequest httpServletRequest, @RequestBody StationRequest stationRequest) {

        boolean validated = authenticationService.validateTokenAndRole(httpServletRequest,  RoleEnum.ADMIN);
        if(!validated) return null;

        StateResponse stateResponse = new StateResponse();
        try {
            stationsService.updateStation(stationRequest);
            stateResponse.setSuccess(true);
        }
        catch (Exception e) {
            e.printStackTrace();
            stateResponse.setSuccess(false);
        }
        return stateResponse;
    }

    /**
     * it will delete a station with a given id
     * @param httpServletRequest
     * @param id
     * @return
     */
    @RequestMapping(path = "/deleteStation",method = RequestMethod.POST)
    public StateResponse deleteStation(HttpServletRequest httpServletRequest, @RequestBody RequestWithIdOnly id) {

        boolean validated = authenticationService.validateTokenAndRole(httpServletRequest, RoleEnum.ADMIN);
        if(!validated) return null;

        StateResponse stateResponse = new StateResponse();
        try {
            stationsService.deleteStation(id);
            stateResponse.setSuccess(true);
        }
        catch (Exception e) {
            e.printStackTrace();
            stateResponse.setSuccess(false);
        }
        return stateResponse;
    }

    /**
     * it adds a power unit to a station
     * @param httpServletRequest
     * @param assignPowerUnitToStation
     * @return stateResponse
     *//*
    @RequestMapping(path = "/addPowerUnitToStation",method = RequestMethod.POST)
    public StateResponse addPowerUnitToStation(HttpServletRequest httpServletRequest, @RequestBody AssignPowerUnitToStation assignPowerUnitToStation) {

        boolean validated = authenticationService.validateTokenAndRole(httpServletRequest, RoleEnum.ADMIN);
        if(!validated) return null;

        StateResponse stateResponse = new StateResponse();
        try {
            stationsService.addPowerUnitToStation(assignPowerUnitToStation);
            stateResponse.setSuccess(true);
        }
        catch (Exception e) {
            e.printStackTrace();
            stateResponse.setSuccess(false);
        }
        return stateResponse;
    }*/
}
