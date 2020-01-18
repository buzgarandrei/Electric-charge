
package com.example.demo.controllers;

import com.example.demo.entities.enums.RoleEnum;
import com.example.demo.request.CarRequest;
import com.example.demo.request.specialRequests.RequestWithIdOnly;
import com.example.demo.response.CarResponse;
import com.example.demo.response.StateResponse;
import com.example.demo.services.AuthenticationService;
import com.example.demo.services.CarsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Controller for cars, will have CRUD operations and for cars and possibility to get a car by a given id.
 *
 */
@RestController
public class CarsController {

    @Autowired
    private CarsService carsService;

    @Autowired
    AuthenticationService authenticationService;

    /**
     *Will turn back the whole list of cars in database
     * @param httpServletRequest
     * @return carsResponseList
     */
    @RequestMapping("/getCarsList")
    public List<CarResponse> getCarsList(HttpServletRequest httpServletRequest) {

        boolean validated = authenticationService.validateTokenAndRole(httpServletRequest, null);
        if(!validated) return null;

        List<CarResponse> carResponseList = carsService.findAll();
        return carResponseList;
    }

    /**
     * We give an id and we will get the car that has that id.
     @param httpServletRequest
     @param request
     @return carsResponse
      */
    @RequestMapping("/getCarById")
    public CarResponse getCarById(HttpServletRequest httpServletRequest, @RequestBody RequestWithIdOnly request) {

        boolean validated = authenticationService.validateTokenAndRole(httpServletRequest, RoleEnum.ADMIN);
        if(!validated) return null;

        CarResponse carResponse = carsService.getCarById(request);
        return carResponse;
    }

    /**
     * You give a carsRequest , a car will be created by that dto and it will be added in db
     * @param httpServletRequest
     * @param carRequest
     * @return
     */
    @RequestMapping(value = "/addCar",method = RequestMethod.POST)
    public StateResponse addCar(HttpServletRequest httpServletRequest, @RequestBody CarRequest carRequest) {

        boolean validated = authenticationService.validateTokenAndRole(httpServletRequest, null);
        if(!validated) return null;

        StateResponse stateResponse = new StateResponse();

        try {
            if(carsService.addCar(carRequest).isSuccess())
                stateResponse.setSuccess(true);
            else stateResponse.setSuccess(false);
        } catch (Exception e) {
            e.printStackTrace();
            stateResponse.setSuccess(false);
        }
        return stateResponse;
    }

    /**
     *
     * @param httpServletRequest
     * @param carRequest
     * @return stateResponse
     */
    @RequestMapping(value = "/updateCar",method = RequestMethod.POST)
    public StateResponse updateCar(HttpServletRequest httpServletRequest, @RequestBody CarRequest carRequest) {

        boolean validated = authenticationService.validateTokenAndRole(httpServletRequest,RoleEnum.ADMIN);
        if(!validated) return null;

        StateResponse stateResponse = new StateResponse();

        try {
            if(carsService.updateCar(carRequest).isSuccess())
                stateResponse.setSuccess(true);
            else stateResponse.setSuccess(false);
        } catch (Exception e) {
            e.printStackTrace();
            stateResponse.setSuccess(false);
        }
        return stateResponse;
    }

    /**
     * You give an object with an id and will delete the car with that id from db.
     * @param httpServletRequest
     * @param id
     * @return stateResponse
     */
    @RequestMapping(value = "/deleteCar",method = RequestMethod.POST)
    public StateResponse deleteCar(HttpServletRequest httpServletRequest, @RequestBody RequestWithIdOnly id) {

        boolean validated = authenticationService.validateTokenAndRole(httpServletRequest, RoleEnum.ADMIN);
        if(!validated) return null;

        StateResponse stateResponse = new StateResponse();

        try {
            carsService.deleteCar(id);
            stateResponse.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            stateResponse.setSuccess(false);
        }
        return stateResponse;
    }
}
