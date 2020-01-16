package com.example.demo.repositories;

import com.example.demo.entities.ContactFormCategoriesEntity;
import com.example.demo.request.ContactFormCategoriesRequest;
import com.example.demo.request.specialRequests.RequestWithIdOnly;
import com.example.demo.response.ContactFormCategoriesResponse;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class ContactFormCategoriesRepositoryImpl implements ContactFormCategoriesRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public List<ContactFormCategoriesEntity> findAll() {

        Query query = entityManager.createQuery("select cfg from ContactFormCategoriesEntity cfg", ContactFormCategoriesEntity.class);
        List<ContactFormCategoriesEntity> resultList = query.getResultList();
        return resultList;
    }

    @Override
    @Transactional
    public void addContactFormCategories(ContactFormCategoriesRequest contactFormCategoriesRequest) throws Exception {

        try {

            ContactFormCategoriesEntity contactFormCategoriesEntity = new ContactFormCategoriesEntity();
            contactFormCategoriesEntity.setTypeOfCategory(contactFormCategoriesRequest.getTypeOfCategory());
            entityManager.persist(contactFormCategoriesEntity);

        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("bad category given");
            throw e;
        }

    }

    @Override
    @Transactional
    public void updateContactFormCategories(ContactFormCategoriesRequest contactFormCategoriesRequest) throws Exception {

        try {
            ContactFormCategoriesEntity contactFormCategoriesEntity = entityManager.find(ContactFormCategoriesEntity.class,contactFormCategoriesRequest.getId());
            if(contactFormCategoriesRequest.getTypeOfCategory() != null)
                contactFormCategoriesEntity.setTypeOfCategory(contactFormCategoriesRequest.getTypeOfCategory());

            entityManager.merge(contactFormCategoriesEntity);
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("bad category given");
            throw e;
        }
    }

    @Override
    @Transactional
    public void deleteContactFormCategories(RequestWithIdOnly id) throws Exception {

        try {
            ContactFormCategoriesEntity contactFormCategoriesEntity = entityManager.find(ContactFormCategoriesEntity.class,id.getId());
            entityManager.remove(contactFormCategoriesEntity);
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("bad category given  ");
            throw e;
        }
    }

    @Override
    public ContactFormCategoriesResponse getContactFormCategoriesById(RequestWithIdOnly request) {

        ContactFormCategoriesEntity entity = entityManager.find(ContactFormCategoriesEntity.class,request.getId());
        ContactFormCategoriesResponse response = new ContactFormCategoriesResponse();

        response.setId(entity.getId());
        response.setTypeOfCategory(entity.getTypeOfCategory());

        return response;
    }


}
