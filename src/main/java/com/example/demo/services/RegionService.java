package com.example.demo.services;

import com.example.demo.request.RegionRequest;
import com.example.demo.request.specialRequests.RequestWithIdOnly;
import com.example.demo.response.RegionResponse;

import java.util.List;

public interface RegionService {

    public void addRegion(RegionRequest regionRequest) throws Exception;

    void deleteRegion(RequestWithIdOnly requestWithIdOnly) throws Exception;

    void updateRegion(RegionRequest regionRequest) throws Exception;

    List<RegionResponse> getRegions() throws Exception;

    RegionResponse getRegionById(RequestWithIdOnly requestWithIdOnly) throws Exception;

    RegionResponse getRegion(RegionRequest regionRequest) throws Exception;

    List<RegionResponse> getRegionsOfACountry(RegionRequest regionRequest) throws Exception;
}
