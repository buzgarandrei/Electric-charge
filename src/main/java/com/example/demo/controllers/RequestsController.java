package com.example.demo.controllers;

import com.example.demo.entities.enums.RoleEnum;
import com.example.demo.request.ChargerequestRequest;
import com.example.demo.request.specialRequests.RequestWithIdOnly;
import com.example.demo.response.ChargerequestResponse;
import com.example.demo.response.StateResponse;
import com.example.demo.services.AuthenticationService;
import com.example.demo.services.ChargeRequestsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class RequestsController {

    @Autowired
    ChargeRequestsServices chargeRequestsServices;

    @Autowired
    AuthenticationService authenticationService;

    /**
     * it will bring the list of all requests of charging from db
     * @param httpServletRequest
     * @return
     */
    @RequestMapping("/getRequestsList")
    public List<ChargerequestResponse> getChargerequestList(HttpServletRequest httpServletRequest) {

        boolean validated = authenticationService.validateTokenAndRole(httpServletRequest, null);
        if(!validated) return null;

        return chargeRequestsServices.findAll();
    }

    /**
     * it will return the charge request that has the given id
     * @param httpServletRequest
     * @param request
     * @return
     */
    @RequestMapping("/getRequestById")
    public ChargerequestResponse getRequestById(HttpServletRequest httpServletRequest,@RequestBody RequestWithIdOnly request) {

        boolean validated = authenticationService.validateTokenAndRole(httpServletRequest, null);
        if(!validated) return null;

        return chargeRequestsServices.getRequestById(request);
    }

    /**
     * it will add in db a charge request
     * @param httpServletRequest
     * @param chargerequestRequest
     * @return
     */
    @RequestMapping(path = "/addRequest",method = RequestMethod.POST)
    public StateResponse addChargerequest(HttpServletRequest httpServletRequest, @RequestBody ChargerequestRequest chargerequestRequest) {

        boolean validated = authenticationService.validateTokenAndRole(httpServletRequest, null);
        if(!validated) return null;

        StateResponse stateResponse = new StateResponse();
        try {
            chargeRequestsServices.addChargerequest(chargerequestRequest);
            stateResponse.setSuccess(true);
        }
        catch (Exception e) {
            e.printStackTrace();
            stateResponse.setSuccess(false);
        }
        return stateResponse;
    }

    /**
     * it will update a charge request
     * @param httpServletRequest
     * @param chargerequestRequest
     * @return
     */
    @RequestMapping(path = "/updateRequest",method = RequestMethod.POST)
    public StateResponse updateChargerequest(HttpServletRequest httpServletRequest, @RequestBody ChargerequestRequest chargerequestRequest) {

        boolean validated = authenticationService.validateTokenAndRole(httpServletRequest, null);
        if(!validated) return null;

        StateResponse stateResponse = new StateResponse();
        try {
            chargeRequestsServices.updateChargerequest(chargerequestRequest);
            stateResponse.setSuccess(true);
        }
        catch (Exception e) {
            e.printStackTrace();
            stateResponse.setSuccess(false);
        }
        return stateResponse;
    }

    /**
     * it will delete from the db the charge request with the given id
     * @param httpServletRequest
     * @param id
     * @return
     */
    @RequestMapping(path = "/deleteRequest",method = RequestMethod.POST)
    public StateResponse deleteChargerequest(HttpServletRequest httpServletRequest, @RequestBody RequestWithIdOnly id) {

        boolean validated = authenticationService.validateTokenAndRole(httpServletRequest, null);
        if(!validated) return null;

        StateResponse stateResponse = new StateResponse();
        try {
            chargeRequestsServices.deleteChargerequest(id);
            stateResponse.setSuccess(true);
        }
        catch (Exception e) {
            e.printStackTrace();
            stateResponse.setSuccess(false);
        }
        return stateResponse;
    }
}
