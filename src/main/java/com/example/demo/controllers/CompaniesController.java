package com.example.demo.controllers;


import com.example.demo.entities.enums.RoleEnum;
import com.example.demo.request.CompanyRequest;
import com.example.demo.request.specialRequests.RequestWithIdOnly;
import com.example.demo.response.CompaniesResponse;
import com.example.demo.response.StateResponse;
import com.example.demo.services.AuthenticationService;
import com.example.demo.services.CompaniesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class CompaniesController {
    /**
     * a Company is for cars only, it would we useless to give a company when we instantiate a Station
     */
    @Autowired
    CompaniesService companiesService;

    @Autowired
    AuthenticationService authenticationService;

    /**
     * It returns the whole list of companies from db
     *
     * @param httpServletRequest
     * @return companiesResponseList
     */
    @RequestMapping(path = "/getCompaniesList")
    public List<CompaniesResponse> getCompaniesList(HttpServletRequest httpServletRequest) {

        boolean validated = authenticationService.validateTokenAndRole(httpServletRequest, RoleEnum.ADMIN);
        if(!validated) return null;

        List<CompaniesResponse> companiesResponseList = companiesService.findAll();
        return companiesResponseList;
    }

    /**
     * it receives a object with a field id, and the company with that id will be returned
     * @param httpServletRequest
     * @param request
     * @return
     */
    @RequestMapping("/getCompanyById")
    public CompaniesResponse getCompanyById(HttpServletRequest httpServletRequest, @RequestBody RequestWithIdOnly request) {

        boolean validated = authenticationService.validateTokenAndRole(httpServletRequest, RoleEnum.ADMIN);
        if(!validated) return null;

        CompaniesResponse response = companiesService.getCompanyById(request);

        return response;
    }

    /**
     * It will receive an object that will contain the fields for a Company to be added in db
     * First step when you open the uninitialized application, you add a company, then a car, and then user, power units, stations etc
     * @param httpServletRequest
     * @param companyRequest
     * @return stateResponse
     */
    @RequestMapping(path = "/addCompany",method = RequestMethod.POST)
    public StateResponse addCompany(HttpServletRequest httpServletRequest, @RequestBody CompanyRequest companyRequest) {

        boolean validated = authenticationService.validateTokenAndRole(httpServletRequest, RoleEnum.ADMIN);
        if(!validated) return null;

        StateResponse stateResponse = new StateResponse();
        try {
            companiesService.addCompany(companyRequest);
            stateResponse.setSuccess(true);
        }
        catch (Exception e) {
            e.printStackTrace();
            stateResponse.setSuccess(false);
        }
        return stateResponse;
    }

    /**
     * It will update a Company from the db with the given object's fields
     * @param httpServletRequest
     * @param companyRequest
     * @return
     */
    @RequestMapping(path = "/updateCompany",method = RequestMethod.POST)
    public StateResponse updateCompany(HttpServletRequest httpServletRequest, @RequestBody CompanyRequest companyRequest) {

        boolean validated = authenticationService.validateTokenAndRole(httpServletRequest, RoleEnum.ADMIN);
        if(!validated) return null;

        StateResponse stateResponse = new StateResponse();
        try {
            companiesService.updateCompany(companyRequest);
            stateResponse.setSuccess(true);
        }
        catch (Exception e) {
            e.printStackTrace();
            stateResponse.setSuccess(false);
        }
        return stateResponse;
    }

    /**
     * It will delete the company with the given id from db
     * @param httpServletRequest
     * @param id
     * @return stateResponse
     */
    @RequestMapping(path = "/deleteCompany",method = RequestMethod.POST)
    public StateResponse deleteCompany(HttpServletRequest httpServletRequest, @RequestBody RequestWithIdOnly id) {

        boolean validated = authenticationService.validateTokenAndRole(httpServletRequest, RoleEnum.ADMIN);
        if(!validated) return null;

        StateResponse stateResponse = new StateResponse();
        try {
            companiesService.deleteCompany(id);
            stateResponse.setSuccess(true);
        }
        catch (Exception e) {
            e.printStackTrace();
            stateResponse.setSuccess(false);
        }
        return stateResponse;
    }
}
