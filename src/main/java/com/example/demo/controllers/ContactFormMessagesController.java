package com.example.demo.controllers;

import com.example.demo.entities.enums.RoleEnum;
import com.example.demo.request.ContactFormMessagesRequest;
import com.example.demo.request.specialRequests.RequestWithIdOnly;
import com.example.demo.response.ContactFormMessagesResponse;
import com.example.demo.response.StateResponse;
import com.example.demo.services.AuthenticationService;
import com.example.demo.services.ContactFormMessagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class ContactFormMessagesController {

    @Autowired
    ContactFormMessagesService contactFormMessagesService;

    @Autowired
    AuthenticationService authenticationService;

    /**
     * It will return the whole list of  contact form messages from db
     * @param httpServletRequest
     * @return
     */
    @RequestMapping(value = "/getContactFormMessages")
    public List<ContactFormMessagesResponse> getContactFormMessages(HttpServletRequest httpServletRequest) {

        boolean validated = authenticationService.validateTokenAndRole(httpServletRequest, RoleEnum.ADMIN);
        if(!validated) return null;

        List<ContactFormMessagesResponse> contactFormMessagesResponseList = contactFormMessagesService.findAll();

        return contactFormMessagesResponseList;
    }

    /**
     * It will return a specified mesage with an id from db
     * @param httpServletRequest
     * @param request
     * @return
     */
    @RequestMapping(value = "/getContactFormMessageById")
    public ContactFormMessagesResponse getContactFormMessageById(HttpServletRequest httpServletRequest, @RequestBody RequestWithIdOnly request) {

        boolean validated = authenticationService.validateTokenAndRole(httpServletRequest, RoleEnum.ADMIN);
        if(!validated) return null;

        return contactFormMessagesService.getContactFormMessageById(request);
    }

    /**
     * It will add a message to db
     * @param httpServletRequest
     * @param contactFormMessagesRequest
     * @return
     */
    @RequestMapping(path = "/addContactFormMessages",method = RequestMethod.POST)
    public StateResponse addContactFormsMessages(HttpServletRequest httpServletRequest, @RequestBody ContactFormMessagesRequest contactFormMessagesRequest) {

        boolean validated = authenticationService.validateTokenAndRole(httpServletRequest, null);
        if(!validated) return null;

        StateResponse stateResponse = new StateResponse();
        try {
            contactFormMessagesService.addContactFormCategories(contactFormMessagesRequest);
            stateResponse.setSuccess(true);
        }
        catch (Exception e) {
            e.printStackTrace();
            stateResponse.setSuccess(false);
        }
        return stateResponse;
    }

    /**
     * it will update a message from db
     * @param httpServletRequest
     * @param contactFormMessagesRequest
     * @return
     */
    @RequestMapping(path = "/updateContactFormMessages",method = RequestMethod.POST)
    public StateResponse updateContactFormsMessages(HttpServletRequest httpServletRequest, @RequestBody ContactFormMessagesRequest contactFormMessagesRequest) {

        boolean validated = authenticationService.validateTokenAndRole(httpServletRequest, RoleEnum.ADMIN);
        if(!validated) return null;

        StateResponse stateResponse = new StateResponse();
        try {
            contactFormMessagesService.updateContactFormMessages(contactFormMessagesRequest);
            stateResponse.setSuccess(true);
        }
        catch (Exception e) {
            e.printStackTrace();
            stateResponse.setSuccess(false);
        }
        return stateResponse;
    }

    /**
     * it will delete a message from db with the given id
     * @param httpServletRequest
     * @param id
     * @return
     */
    @RequestMapping(path = "/deleteContactFormMessages",method = RequestMethod.POST)
    public StateResponse deleteContactFormsMessages(HttpServletRequest httpServletRequest, @RequestBody RequestWithIdOnly id) {

        boolean validated = authenticationService.validateTokenAndRole(httpServletRequest, null);
        if(!validated) return null;

        StateResponse stateResponse = new StateResponse();
        try {
            contactFormMessagesService.deleteContactFormMessages(id);
            stateResponse.setSuccess(true);
        }
        catch (Exception e) {
            e.printStackTrace();
            stateResponse.setSuccess(false);
        }
        return stateResponse;
    }
}


