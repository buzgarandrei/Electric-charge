package com.example.demo.services;

import com.example.demo.request.ContactFormCategoriesRequest;
import com.example.demo.request.specialRequests.RequestWithIdOnly;
import com.example.demo.response.ContactFormCategoriesResponse;

import java.util.List;

public interface ContactFormCategoriesService {


    public List<ContactFormCategoriesResponse> findAll();

    public void addContactFormCategories(ContactFormCategoriesRequest contactFormCategoriesRequest) throws Exception;

    public void updateContactFormCategories(ContactFormCategoriesRequest contactFormCategoriesRequest) throws Exception;

    public void deleteContactFormCategories(RequestWithIdOnly id) throws Exception;

    ContactFormCategoriesResponse getContactFormCategoriesById(RequestWithIdOnly request);
}
