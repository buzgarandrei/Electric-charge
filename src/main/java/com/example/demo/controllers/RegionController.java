package com.example.demo.controllers;

import com.example.demo.entities.enums.RoleEnum;
import com.example.demo.request.RegionRequest;
import com.example.demo.request.specialRequests.RequestWithIdOnly;
import com.example.demo.response.RegionResponse;
import com.example.demo.response.StateResponse;
import com.example.demo.services.AuthenticationService;
import com.example.demo.services.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class RegionController {

    @Autowired
    RegionService regionService;

    @Autowired
    AuthenticationService authenticationService;

    /**
     * it will bring the whole list of regions from db
     * @param httpServletRequest
     * @return
     * @throws Exception
     */
    @RequestMapping(path = "/getRegions", method = RequestMethod.GET)
    public List<RegionResponse> getRegions(HttpServletRequest httpServletRequest) throws Exception {

        boolean validated = authenticationService.validateTokenAndRole(httpServletRequest, RoleEnum.ADMIN);
        if(!validated) return null;

        List<RegionResponse> regionResponseList = regionService.getRegions();

        return regionResponseList;
    }

    /**
     * it will add a region in db
     * @param httpServletRequest
     * @param regionRequest
     * @return
     */
    @RequestMapping(path = "/addRegion", method = RequestMethod.POST)
    public StateResponse addRegion(HttpServletRequest httpServletRequest, @RequestBody RegionRequest regionRequest) {

        boolean validated = authenticationService.validateTokenAndRole(httpServletRequest,null);
        if(!validated) return null;

        StateResponse stateResponse = new StateResponse();
        try {
            regionService.addRegion(regionRequest);
            stateResponse.setSuccess(true);
        }
        catch (Exception e) {
            stateResponse.setSuccess(false);
            e.printStackTrace();
        }
        return stateResponse;
    }

    /**
     * it will delete a region with a specified id form db
     * @param httpServletRequest
     * @param requestWithIdOnly
     * @return
     */

    @RequestMapping(path = "/deleteRegion", method = RequestMethod.POST)
    public StateResponse deleteRegion(HttpServletRequest httpServletRequest, @RequestBody RequestWithIdOnly requestWithIdOnly) {

        boolean validated = authenticationService.validateTokenAndRole(httpServletRequest, RoleEnum.ADMIN);
        if(!validated) return null;

        StateResponse stateResponse = new StateResponse();
        try {
            regionService.deleteRegion(requestWithIdOnly);
            stateResponse.setSuccess(true);
        }
        catch (Exception e) {
            stateResponse.setSuccess(false);
            e.printStackTrace();
        }
        return stateResponse;
    }

    /**
     * it will update a region from db
     * @param httpServletRequest
     * @param regionRequest
     * @return
     */
    @RequestMapping(path = "/updateRegion", method = RequestMethod.POST)
    public StateResponse updateRegion(HttpServletRequest httpServletRequest, @RequestBody RegionRequest regionRequest) {

        boolean validated = authenticationService.validateTokenAndRole(httpServletRequest, RoleEnum.ADMIN);
        if (!validated) return null;

        StateResponse stateResponse = new StateResponse();
        try {
            regionService.updateRegion(regionRequest);
            stateResponse.setSuccess(true);
        } catch (Exception e) {
            stateResponse.setSuccess(false);
            e.printStackTrace();
        }
        return stateResponse;
    }

    /**
     * it will return a region with a specified id
     * @param httpServletRequest
     * @param requestWithIdOnly
     * @return
     * @throws Exception
     */
    @RequestMapping(path = "/getRegionById", method = RequestMethod.GET)
    public RegionResponse getRegionById(HttpServletRequest httpServletRequest,@RequestBody RequestWithIdOnly requestWithIdOnly) throws Exception {

        boolean validated = authenticationService.validateTokenAndRole(httpServletRequest, RoleEnum.ADMIN);
        if(!validated) return null;

        return regionService.getRegionById(requestWithIdOnly);

    }

    /**
     * it will get a region by the name of the country and the name of the city, useful for confirmation of a search
     * @param httpServletRequest
     * @param regionRequest
     * @return
     * @throws Exception
     */
    @RequestMapping(path = "/getRegion",method = RequestMethod.GET)
    public RegionResponse getRegion(HttpServletRequest httpServletRequest,@RequestBody RegionRequest regionRequest) throws Exception {

        boolean validated = authenticationService.validateTokenAndRole(httpServletRequest, null);
        if(!validated) return null;

        return regionService.getRegion(regionRequest);
    }

    /**
     * it bring a list of all the regions that have the field country equal with a given one
     * @param httpServletRequest
     * @param regionRequest
     * @return
     * @throws Exception
     */
    @RequestMapping(path = "/getRegionsOfACountry",method = RequestMethod.GET)
    public List<RegionResponse> getRegionsOfACountry(HttpServletRequest httpServletRequest,@RequestBody RegionRequest regionRequest) throws Exception {

        boolean validated = authenticationService.validateTokenAndRole(httpServletRequest, null);
        if(!validated) return null;

        return regionService.getRegionsOfACountry(regionRequest);
    }

}
