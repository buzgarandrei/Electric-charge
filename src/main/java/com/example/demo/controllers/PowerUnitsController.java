package com.example.demo.controllers;

import com.example.demo.entities.enums.RoleEnum;
import com.example.demo.request.PowerUnitsRequest;
import com.example.demo.request.specialRequests.*;
import com.example.demo.response.PowerUnitsResponse;
import com.example.demo.response.StateResponse;
import com.example.demo.response.specialResponses.CheckOutResponse;
import com.example.demo.response.specialResponses.GoingToQueueResponse;
import com.example.demo.services.AuthenticationService;
import com.example.demo.services.PowerUnitsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;

@RestController
public class PowerUnitsController {

    @Autowired
    PowerUnitsService powerUnitsService;

    @Autowired
    AuthenticationService authenticationService;

    /**
     * it will bring the list of all power units in db
     * @param httpServletRequest
     * @return
     */
    @RequestMapping("/getPowerUnitsList")
    public List<PowerUnitsResponse> getPowerUnitsList(HttpServletRequest httpServletRequest) {

        boolean validated = authenticationService.validateTokenAndRole(httpServletRequest,RoleEnum.ADMIN);
        if(!validated) return null;

        return powerUnitsService.findAll();
    }

    /**
     * it will return the power unit with the given id
     * @param httpServletRequest
     * @param request
     * @return
     */
    @RequestMapping("/getPowerUnitById")
    public PowerUnitsResponse getPowerUnitById(HttpServletRequest httpServletRequest, @RequestBody RequestWithIdOnly request) {

        boolean validated = authenticationService.validateTokenAndRole(httpServletRequest,RoleEnum.ADMIN);
        if(!validated) return null;

        return powerUnitsService.getPowerUnitById(request);
    }

    /**
     * it will add a power unit in db
     * @param httpServletRequest
     * @param powerUnitsRequest
     * @return
     */
    @RequestMapping(path = "/addPowerUnit",method = RequestMethod.POST)
    public StateResponse addPowerUnit(HttpServletRequest httpServletRequest, @RequestBody PowerUnitsRequest powerUnitsRequest) {

        boolean validated = authenticationService.validateTokenAndRole(httpServletRequest,RoleEnum.ADMIN);
        if(!validated) return null;

        StateResponse stateResponse = new StateResponse();
        try {
            powerUnitsService.addPowerUnit(powerUnitsRequest);
            stateResponse.setSuccess(true);
        }
        catch (Exception e) {
            e.printStackTrace();
            stateResponse.setSuccess(false);
        }
        return stateResponse;
    }

    /**
     * it will update a power unit in db
     * @param httpServletRequest
     * @param powerUnitsRequest
     * @return
     */
    @RequestMapping(path = "/updatePowerUnit",method = RequestMethod.POST)
    public StateResponse updatePowerUnit(HttpServletRequest httpServletRequest, @RequestBody PowerUnitsRequest powerUnitsRequest) {

        boolean validated = authenticationService.validateTokenAndRole(httpServletRequest,RoleEnum.ADMIN);
        if(!validated) return null;

        StateResponse stateResponse = new StateResponse();
        try {
            powerUnitsService.updatePowerUnit(powerUnitsRequest);
            stateResponse.setSuccess(true);
        }
        catch (Exception e) {
            e.printStackTrace();
            stateResponse.setSuccess(false);
        }
        return stateResponse;
    }

    /**
     * it will delete a power unit from db
     * @param httpServletRequest
     * @param id
     * @return
     */
    @RequestMapping(path = "/deletePowerUnit",method = RequestMethod.POST)
    public StateResponse deletePowerUnit(HttpServletRequest httpServletRequest, @RequestBody RequestWithIdOnly id) {

        boolean validated = authenticationService.validateTokenAndRole(httpServletRequest,RoleEnum.ADMIN);
        if(!validated) return null;

        StateResponse stateResponse = new StateResponse();
        try {
            powerUnitsService.deletePowerUnit(id);
            stateResponse.setSuccess(true);
        }
        catch (Exception e) {
            e.printStackTrace();
            stateResponse.setSuccess(false);
        }
        return stateResponse;
    }

    /**
     * it will make an appointment at a power unit and will return the details of the made appointment
     * @param servletRequest
     * @param goingToQueueRequest
     * @return
     * @throws Exception
    @RequestMapping(path = "/makeAppointmentAtPowerUnit",method = RequestMethod.POST)
    public GoingToQueueResponse makeAppointmentAtPowerUnit(HttpServletRequest servletRequest, @RequestBody GoingToQueueRequest goingToQueueRequest) throws Exception {

        return powerUnitsService.makeAppointmentAtPowerUnit(goingToQueueRequest);

    }

    *//**
     * will return the list of the appointments made at a power unit
     * @param servletRequest
     * @param requestWithIdOnly
     * @return
     * @throws Exception
     *//*
    @RequestMapping(path = "/getAppointmentsAtAPowerUnit")
    public List<GoingToQueueResponse> getAppointmentsAtAPowerUnit(HttpServletRequest servletRequest,@RequestBody RequestWithIdOnly requestWithIdOnly) throws Exception {

        boolean validated = authenticationService.validateTokenAndRole(servletRequest,RoleEnum.ADMIN);
        if(!validated) return null;

        List<GoingToQueueResponse> list = powerUnitsService.getAppointmentsAtAPowerUnit(requestWithIdOnly);
        return list;
    }

    *//**
     * will return the appointment with the given id
     * @param httpServletRequest
     * @param requestWithIdOnly
     * @return
     * @throws Exception
     *//*
    @RequestMapping(path = "/getAppointmentById")
    public GoingToQueueResponse getAppointmentById(HttpServletRequest httpServletRequest,@RequestBody RequestWithIdOnly requestWithIdOnly) throws Exception {

        boolean validated = authenticationService.validateTokenAndRole(httpServletRequest,RoleEnum.ADMIN);
        if(!validated) return null;

        return powerUnitsService.getAppointmentById(requestWithIdOnly);
    }

    *//**
     * will return a list with all appointments from db
     * @param httpServletRequest
     * @return
     * @throws Exception
     *//*
    @RequestMapping(path = "/getAllAppointments")
    public List<GoingToQueueResponse> getAllAppointments(HttpServletRequest httpServletRequest) throws Exception {

        boolean validated = authenticationService.validateTokenAndRole(httpServletRequest,RoleEnum.ADMIN);
        if(!validated) return null;

        return powerUnitsService.getAllAppointments();
    }

    *//**
     * will delete an appointment with a given id from db
     * @param httpServletRequest
     * @param requestWithIdOnly
     * @return
     *//*
    @RequestMapping(value = "/deleteAppointment",method = RequestMethod.POST)
    public StateResponse deleteAppointment(HttpServletRequest httpServletRequest, @RequestBody RequestWithIdOnly requestWithIdOnly) {

        boolean validated = authenticationService.validateTokenAndRole(httpServletRequest,RoleEnum.ADMIN);
        if(!validated) return null;

        StateResponse stateResponse =new StateResponse();
        try {
            powerUnitsService.deleteAppointment(requestWithIdOnly);
            stateResponse.setSuccess(true);
        }
        catch (Exception e) {
            e.printStackTrace();
            stateResponse.setSuccess(false);
        }
        return stateResponse;
    }

    *//**
     * will update an appointment
     * @param httpServletRequest
     * @param request
     * @return
     *//*
    @RequestMapping(value = "/updateAppointment",method = RequestMethod.POST)
    public StateResponse updateAppointment(HttpServletRequest httpServletRequest,@RequestBody UpdateToQueue request) {

        boolean validated = authenticationService.validateTokenAndRole(httpServletRequest,RoleEnum.ADMIN);
        if(!validated) return null;

        StateResponse stateResponse = new StateResponse();
        try {
            powerUnitsService.updateAppointment(request);
            stateResponse.setSuccess(true);
        }
        catch (Exception e) {
            e.printStackTrace();
            stateResponse.setSuccess(false);
        }
        return stateResponse;
    }*/

    @RequestMapping(value = "/doCheckIn",method = RequestMethod.POST)
    public StateResponse doCheckIn(HttpServletRequest httpServletRequest, @RequestBody CheckInRequest request) {

        boolean validated = authenticationService.validateTokenAndRole(httpServletRequest, null);
        if (!validated) return null;

        StateResponse stateResponse = new StateResponse();
        try {
            StateResponse response = powerUnitsService.doCheckIn(request);
            if (response.isSuccess() == true)
                stateResponse.setSuccess(true);
            else
                stateResponse.setSuccess(false);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return stateResponse;
    }

        @RequestMapping(value = "/doCheckOut",method = RequestMethod.POST)
    public CheckOutResponse doCheckOut(HttpServletRequest httpServletRequest, @RequestBody CheckOutRequest request) {

        boolean validated = authenticationService.validateTokenAndRole(httpServletRequest, null);
        if (!validated) return null;

        CheckOutResponse response = new CheckOutResponse();
        try {
            response.setAmount(powerUnitsService.doCheckOut(request));
        }
        catch (Exception e) {
            e.printStackTrace();
            response.setAmount(null);
        }
        return response;
    }

    @RequestMapping(path = "/deleteCheckOut", method = RequestMethod.POST)
        public StateResponse updatePowerUnit(HttpServletRequest httpServletRequest, @RequestBody RequestWithIdOnly request) {

            boolean validated = authenticationService.validateTokenAndRole(httpServletRequest,RoleEnum.ADMIN);
            if(!validated) return null;

            StateResponse stateResponse = new StateResponse();
            try {
                powerUnitsService.deleteCheckOut(request);
                stateResponse.setSuccess(true);
            }
            catch (Exception e) {
                e.printStackTrace();
                stateResponse.setSuccess(false);
            }
            return stateResponse;
        }
}
