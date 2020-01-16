package com.example.demo.services;

import com.example.demo.entities.ContactFormCategoriesEntity;
import com.example.demo.repositories.ContactFormCategoriesRepository;
import com.example.demo.request.ContactFormCategoriesRequest;
import com.example.demo.request.specialRequests.RequestWithIdOnly;
import com.example.demo.response.ContactFormCategoriesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContactFormCategoriesServiceImpl implements ContactFormCategoriesService {

    @Autowired
    ContactFormCategoriesRepository contactFormCategoriesRepository;


    @Override
    public List<ContactFormCategoriesResponse> findAll() {

        List<ContactFormCategoriesResponse> contactFormCategoriesResponseList= new ArrayList<>();
        List<ContactFormCategoriesEntity> contactFormCategoriesEntityList = contactFormCategoriesRepository.findAll();
        for (ContactFormCategoriesEntity entity : contactFormCategoriesEntityList) {

            ContactFormCategoriesResponse response = new ContactFormCategoriesResponse();
            response.setTypeOfCategory(entity.getTypeOfCategory());
            response.setId(entity.getId());

            contactFormCategoriesResponseList.add(response);
        }

        return contactFormCategoriesResponseList;
    }

    @Override
    public void deleteContactFormCategories(RequestWithIdOnly id) throws Exception {

        contactFormCategoriesRepository.deleteContactFormCategories(id);
    }

    @Override
    public ContactFormCategoriesResponse getContactFormCategoriesById(RequestWithIdOnly request) {

        return contactFormCategoriesRepository.getContactFormCategoriesById(request);

    }

    @Override
    public void updateContactFormCategories(ContactFormCategoriesRequest contactFormCategoriesRequest) throws Exception {

        contactFormCategoriesRepository.updateContactFormCategories(contactFormCategoriesRequest);
    }

    @Override
    public void addContactFormCategories(ContactFormCategoriesRequest contactFormCategoriesRequest) throws Exception {

        contactFormCategoriesRepository.addContactFormCategories(contactFormCategoriesRequest);

    }
}
