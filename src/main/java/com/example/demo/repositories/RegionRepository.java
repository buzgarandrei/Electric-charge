package com.example.demo.repositories;

import com.example.demo.entities.Region;
import com.example.demo.request.RegionRequest;
import com.example.demo.request.specialRequests.RequestWithIdOnly;

import java.util.List;

public interface RegionRepository {

    public void addRegion(RegionRequest regionRequest) throws Exception;

    void deleteRegion(RequestWithIdOnly requestWithIdOnly) throws Exception;

    void updateRegion(RegionRequest regionRequest) throws Exception;

    List<Region> getRegions() throws Exception;

    Region getRegionById(RequestWithIdOnly requestWithIdOnly) throws Exception;

    Region getRegion(RegionRequest regionRequest)throws Exception;

    List<Region> getRegionsOfACountry(RegionRequest regionRequest) throws Exception;
}
