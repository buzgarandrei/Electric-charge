package com.example.demo.services;

import com.example.demo.request.ContactFormMessagesRequest;
import com.example.demo.request.specialRequests.RequestWithIdOnly;
import com.example.demo.response.ContactFormMessagesResponse;

import java.util.List;

public interface ContactFormMessagesService {
    List<ContactFormMessagesResponse> findAll();

    public void addContactFormCategories(ContactFormMessagesRequest contactFormMessagesRequest) throws Exception;

    public void updateContactFormMessages(ContactFormMessagesRequest contactFormMessagesRequest) throws Exception;

    public void deleteContactFormMessages(RequestWithIdOnly id) throws Exception;

    ContactFormMessagesResponse getContactFormMessageById(RequestWithIdOnly request);
}
