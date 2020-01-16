package com.example.demo.services;

import com.example.demo.request.PowerUnitsRequest;
import com.example.demo.request.specialRequests.*;
import com.example.demo.response.PowerUnitsResponse;
import com.example.demo.response.StateResponse;
import com.example.demo.response.specialResponses.GoingToQueueResponse;

import java.text.ParseException;
import java.util.List;

public interface PowerUnitsService {
    public List<PowerUnitsResponse> findAll();

    public void addPowerUnit(PowerUnitsRequest powerUnitsRequest)throws Exception;

    public void updatePowerUnit(PowerUnitsRequest powerUnitsRequest)throws Exception;

    public void deletePowerUnit(RequestWithIdOnly id)throws Exception;

    PowerUnitsResponse getPowerUnitById(RequestWithIdOnly request);

   /* GoingToQueueResponse makeAppointmentAtPowerUnit(GoingToQueueRequest goingToQueueRequest) throws Exception;

    List<GoingToQueueResponse> getAppointmentsAtAPowerUnit(RequestWithIdOnly requestWithIdOnly) throws Exception;

    GoingToQueueResponse getAppointmentById(RequestWithIdOnly requestWithIdOnly) throws Exception;

    List<GoingToQueueResponse> getAllAppointments() throws Exception;

    void deleteAppointment(RequestWithIdOnly requestWithIdOnly) throws Exception;

    void updateAppointment(UpdateToQueue request) throws Exception;*/

    StateResponse doCheckIn(CheckInRequest request) throws ParseException;

    Float doCheckOut(CheckOutRequest request);

    void deleteCheckOut(RequestWithIdOnly request);
}
