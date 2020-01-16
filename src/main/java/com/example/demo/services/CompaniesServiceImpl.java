package com.example.demo.services;

import com.example.demo.entities.CompaniesEntity;
import com.example.demo.repositories.CompaniesRepository;
import com.example.demo.request.CompanyRequest;
import com.example.demo.request.specialRequests.RequestWithIdOnly;
import com.example.demo.response.CompaniesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompaniesServiceImpl implements CompaniesService {

    @Autowired
    CompaniesRepository companiesRepository;

    @Override
    public List<CompaniesResponse> findAll() {

    List<CompaniesResponse> companiesResponseList = new ArrayList<>();
    List<CompaniesEntity> companiesEntityList = companiesRepository.getCompaniesList();

    for (CompaniesEntity entity : companiesEntityList) {

        CompaniesResponse companiesResponse = new CompaniesResponse();
        companiesResponse.setId(entity.getId());
        companiesResponse.setName(entity.getName());

        companiesResponseList.add(companiesResponse);

    }

    return companiesResponseList;
    }

    @Override
    public void addCompany(CompanyRequest companyRequest) throws Exception {

        CompaniesEntity companiesEntity = new CompaniesEntity();
        companiesEntity.setName(companyRequest.getName());

        companiesRepository.addCompany(companiesEntity);
    }

    @Override
    public void updateCompany(CompanyRequest companyRequest) throws Exception {

        companiesRepository.updateCompany(companyRequest);
    }

    @Override
    public void deleteCompany(RequestWithIdOnly id) throws Exception {

        companiesRepository.deleteCompany(id);
    }

    @Override
    public CompaniesResponse getCompanyById(RequestWithIdOnly request) {

        CompaniesResponse response = companiesRepository.getCompanyById(request);
        return response;
    }

}
