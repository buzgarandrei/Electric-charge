package com.example.demo.services;

import com.example.demo.request.CompanyRequest;
import com.example.demo.request.specialRequests.RequestWithIdOnly;
import com.example.demo.response.CompaniesResponse;

import java.util.List;

public interface CompaniesService {


    public List<CompaniesResponse> findAll();

    public void addCompany(CompanyRequest companyRequest) throws Exception;

    public void updateCompany(CompanyRequest companyRequest) throws Exception;

    public void deleteCompany(RequestWithIdOnly id) throws Exception;

    CompaniesResponse getCompanyById(RequestWithIdOnly request);
}
