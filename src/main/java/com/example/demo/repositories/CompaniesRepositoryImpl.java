package com.example.demo.repositories;

import com.example.demo.entities.CompaniesEntity;
import com.example.demo.request.CompanyRequest;
import com.example.demo.request.specialRequests.RequestWithIdOnly;
import com.example.demo.response.CompaniesResponse;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class CompaniesRepositoryImpl implements CompaniesRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public List<CompaniesEntity> getCompaniesList() {

        Query query = entityManager.createQuery("select c from CompaniesEntity c", CompaniesEntity.class);
        List<CompaniesEntity>  resultList = query.getResultList();
        return resultList;
    }

    @Override
    @Transactional
    public void addCompany(CompaniesEntity companiesEntity) throws Exception {

        try {

            entityManager.persist(companiesEntity);

        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Bad CompanyRequest given");
            throw e;
        }
    }

    @Override
    @Transactional
    public void updateCompany(CompanyRequest companyRequest) throws Exception {

        try {
            CompaniesEntity companiesEntity = entityManager.find(CompaniesEntity.class,companyRequest.getId());
            if(companyRequest.getName() != null)
                companiesEntity.setName(companyRequest.getName());

            entityManager.merge(companiesEntity);

        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Bad company id");
            throw e;
        }
    }

    @Override
    @Transactional
    public void deleteCompany(RequestWithIdOnly id) throws Exception {

        try {
            CompaniesEntity companiesEntity = entityManager.find(CompaniesEntity.class,id.getId());

            entityManager.remove(companiesEntity);

        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Bad company id");
            throw e;
        }
    }

    @Override
    public CompaniesResponse getCompanyById(RequestWithIdOnly request) {

        CompaniesEntity entity = entityManager.find(CompaniesEntity.class,request.getId());
        CompaniesResponse response = new CompaniesResponse();
        response.setId(entity.getId());
        response.setName(entity.getName());

        return response;
    }
}
