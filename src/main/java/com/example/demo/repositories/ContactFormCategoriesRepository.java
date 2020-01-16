package com.example.demo.repositories;

import com.example.demo.entities.ContactFormCategoriesEntity;
import com.example.demo.request.ContactFormCategoriesRequest;
import com.example.demo.request.specialRequests.RequestWithIdOnly;
import com.example.demo.response.ContactFormCategoriesResponse;

import java.util.List;

public interface ContactFormCategoriesRepository {
    public List<ContactFormCategoriesEntity> findAll();

    void addContactFormCategories(ContactFormCategoriesRequest contactFormCategoriesRequest) throws Exception;

    void updateContactFormCategories(ContactFormCategoriesRequest contactFormCategoriesRequest) throws Exception;

    void deleteContactFormCategories(RequestWithIdOnly id) throws Exception;

    ContactFormCategoriesResponse getContactFormCategoriesById(RequestWithIdOnly request);
}
