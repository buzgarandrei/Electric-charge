package com.example.demo.repositories;

import com.example.demo.entities.CompaniesEntity;
import com.example.demo.request.CompanyRequest;
import com.example.demo.request.specialRequests.RequestWithIdOnly;
import com.example.demo.response.CompaniesResponse;

import java.util.List;

public interface CompaniesRepository {

    public List<CompaniesEntity> getCompaniesList();

    public void addCompany(CompaniesEntity companyRequest) throws Exception;

    public void updateCompany(CompanyRequest companyRequest) throws Exception;

    public void deleteCompany(RequestWithIdOnly id) throws Exception;

    CompaniesResponse getCompanyById(RequestWithIdOnly request);
}
