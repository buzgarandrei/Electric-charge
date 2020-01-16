package com.example.demo.repositories;

import com.example.demo.entities.ContactFormMessagesEntity;
import com.example.demo.request.ContactFormMessagesRequest;
import com.example.demo.request.specialRequests.RequestWithIdOnly;
import com.example.demo.response.ContactFormMessagesResponse;

import java.util.List;

public interface ContactFormMessagesRepository {
    public List<ContactFormMessagesEntity> findAll();

    public void addContactFormMessages(ContactFormMessagesRequest contactFormMessagesRequest) throws Exception;

    public void updateContactFormMessages(ContactFormMessagesRequest contactFormMessagesRequest) throws Exception;

    public void deleteContactFormMessages(RequestWithIdOnly id) throws Exception;

    ContactFormMessagesResponse getContactFormMessageById(RequestWithIdOnly request);
}
