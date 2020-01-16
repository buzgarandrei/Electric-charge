package com.example.demo.controllers;

import com.example.demo.entities.enums.RoleEnum;
import com.example.demo.request.ContactFormCategoriesRequest;
import com.example.demo.request.specialRequests.RequestWithIdOnly;
import com.example.demo.response.ContactFormCategoriesResponse;
import com.example.demo.response.StateResponse;
import com.example.demo.services.AuthenticationService;
import com.example.demo.services.ContactFormCategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ContactFormCategoriesController {

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    ContactFormCategoriesService contactFormCategoriesService;

    /**
     * It will get the whole types of contact forms from db
     * @param httpServletRequest
     * @return
     */
    @RequestMapping(value = "/getContactFormCategories")
    public List<ContactFormCategoriesResponse> getContactFormCategories(HttpServletRequest httpServletRequest) {

        boolean validated = authenticationService.validateTokenAndRole(httpServletRequest, RoleEnum.ADMIN);
        if(!validated) return null;

        List<ContactFormCategoriesResponse> contactFormCategoriesResponseList = contactFormCategoriesService.findAll();

        return contactFormCategoriesResponseList;
    }

    /**
     * it will return the  form from db with the given id
     * @param httpServletRequest
     * @param request
     * @return
     */
    @RequestMapping("/getContactFormCategoriesById")
    public ContactFormCategoriesResponse getContactFormCategoriesById(HttpServletRequest httpServletRequest, @RequestBody RequestWithIdOnly request) {

        boolean validated = authenticationService.validateTokenAndRole(httpServletRequest, RoleEnum.ADMIN);
        if(!validated) return null;

        ContactFormCategoriesResponse response = contactFormCategoriesService.getContactFormCategoriesById(request);

        return response;

    }

    /**
     * it will add a new form in db with the object's fields
     * @param httpServletRequest
     * @param contactFormCategoriesRequest
     * @return stateResponse
     */

    @RequestMapping(path = "/addContactFormCategory",method = RequestMethod.POST)
    public StateResponse addContactFormsCategory(HttpServletRequest httpServletRequest, @RequestBody ContactFormCategoriesRequest contactFormCategoriesRequest) {

        boolean validated = authenticationService.validateTokenAndRole(httpServletRequest, null);
        if(!validated) return null;

        StateResponse stateResponse = new StateResponse();
        try {
            contactFormCategoriesService.addContactFormCategories(contactFormCategoriesRequest);
            stateResponse.setSuccess(true);
        }
        catch (Exception e) {
            e.printStackTrace();
            stateResponse.setSuccess(false);
        }
        return stateResponse;
    }

    /**
     * it will update a contact form from db, specified with the id, and all the fields that we want to update
     * @param httpServletRequest
     * @param contactFormCategoriesRequest
     * @return stateResponse
     */
    @RequestMapping(path = "/updateContactFormCategory",method = RequestMethod.POST)
    public StateResponse updateContactFormsCategory(HttpServletRequest httpServletRequest, @RequestBody ContactFormCategoriesRequest contactFormCategoriesRequest) {

        boolean validated = authenticationService.validateTokenAndRole(httpServletRequest, null);
        if(!validated) return null;

        StateResponse stateResponse = new StateResponse();
        try {
            contactFormCategoriesService.updateContactFormCategories(contactFormCategoriesRequest);
            stateResponse.setSuccess(true);
        }
        catch (Exception e) {
            e.printStackTrace();
            stateResponse.setSuccess(false);
        }
        return stateResponse;
    }

    /**
     * it will delete the form that has the given id from db
     * @param httpServletRequest
     * @param id
     * @return stateResponse
     */
    @RequestMapping(path = "/deleteContactFormCategory",method = RequestMethod.POST)
    public StateResponse deleteContactFormsCategory(HttpServletRequest httpServletRequest, @RequestBody RequestWithIdOnly id) {

        boolean validated = authenticationService.validateTokenAndRole(httpServletRequest, null);
        if(!validated) return null;

        StateResponse stateResponse = new StateResponse();
        try {
            contactFormCategoriesService.deleteContactFormCategories(id);
            stateResponse.setSuccess(true);
        }
        catch (Exception e) {
            e.printStackTrace();
            stateResponse.setSuccess(false);
        }
        return stateResponse;
    }
}
