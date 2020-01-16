package com.example.demo.services;

import com.example.demo.entities.Region;
import com.example.demo.repositories.RegionRepository;
import com.example.demo.request.RegionRequest;
import com.example.demo.request.specialRequests.RequestWithIdOnly;
import com.example.demo.response.RegionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RegionServiceImpl implements RegionService {

    @Autowired
    RegionRepository regionRepository;

    @Override
    public void addRegion(RegionRequest regionRequest) throws Exception {

        regionRepository.addRegion(regionRequest);
    }

    @Override
    public void deleteRegion(RequestWithIdOnly requestWithIdOnly) throws Exception {
        regionRepository.deleteRegion(requestWithIdOnly);
    }

    @Override
    public void updateRegion(RegionRequest regionRequest) throws Exception {
        regionRepository.updateRegion(regionRequest);
    }

    @Override
    public List<RegionResponse> getRegions() throws Exception {

        List<Region> regionList = regionRepository.getRegions();
        List<RegionResponse> regionResponseList = new ArrayList<>();

        for (Region region : regionList) {

            RegionResponse regionResponse = new RegionResponse();

            regionResponse.setCity(region.getCity());
            regionResponse.setCountry(region.getCountry());

            regionResponseList.add(regionResponse);
        }

        return regionResponseList;
    }

    @Override
    public RegionResponse getRegionById(RequestWithIdOnly requestWithIdOnly) throws Exception {

        Region region = regionRepository.getRegionById(requestWithIdOnly);

        RegionResponse regionResponse = new RegionResponse();
        regionResponse.setCity(region.getCity());
        regionResponse.setCountry(region.getCountry());
        regionResponse.setId(region.getId());

        return regionResponse;
    }

    @Override
    public RegionResponse getRegion(RegionRequest regionRequest) throws Exception {

        Region region = regionRepository.getRegion(regionRequest);

        RegionResponse regionResponse = new RegionResponse();

        regionResponse.setCity(region.getCity());
        regionResponse.setCountry(region.getCountry());
        regionResponse.setId(region.getId());

        return regionResponse;
    }

    @Override
    public List<RegionResponse> getRegionsOfACountry(RegionRequest regionRequest) throws Exception {
        List<Region> regionList = regionRepository.getRegionsOfACountry(regionRequest);

        List<RegionResponse> regionResponseList = new ArrayList<>();
        for (Region region : regionList) {
            RegionResponse regionResponse = new RegionResponse();

            regionResponse.setCity(region.getCity());
            regionResponse.setCountry(region.getCountry());
            regionResponse.setId(region.getId());

            regionResponseList.add(regionResponse);
        }

        return regionResponseList;
    }
}
