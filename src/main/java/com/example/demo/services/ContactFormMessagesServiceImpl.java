package com.example.demo.services;

import com.example.demo.entities.ContactFormMessagesEntity;
import com.example.demo.repositories.ContactFormMessagesRepository;
import com.example.demo.request.ContactFormMessagesRequest;
import com.example.demo.request.specialRequests.RequestWithIdOnly;
import com.example.demo.response.ContactFormMessagesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContactFormMessagesServiceImpl implements ContactFormMessagesService {

    @Autowired
    ContactFormMessagesRepository contactFormMessagesRepository;


    @Override
    @Transactional
    public List<ContactFormMessagesResponse> findAll() {
        List<ContactFormMessagesResponse> contactFormMessagesResponseList = new ArrayList<>();
        List<ContactFormMessagesEntity> contactFormMessagesEntityList = contactFormMessagesRepository.findAll();

        for (ContactFormMessagesEntity entity : contactFormMessagesEntityList) {

            ContactFormMessagesResponse response = new ContactFormMessagesResponse();
            response.setMessageOfContactForm(entity.getMessageOfContactForm());
            response.setState(entity.getState());
            try {
                response.setIdContactFormCategoriesEntity(entity.getContactFormCategoriesEntity().getId());
                response.setId(entity.getId());
            }
            catch (NullPointerException e) {
                e.printStackTrace();
                System.out.println("Bad id");
            }

            contactFormMessagesResponseList.add(response);
        }

        return contactFormMessagesResponseList;
    }

    @Override
    public void addContactFormCategories(ContactFormMessagesRequest contactFormMessagesRequest) throws Exception {

        contactFormMessagesRepository.addContactFormMessages(contactFormMessagesRequest);
    }

    @Override
    public void updateContactFormMessages(ContactFormMessagesRequest contactFormMessagesRequest) throws Exception {

        contactFormMessagesRepository.updateContactFormMessages(contactFormMessagesRequest);
    }

    @Override
    public void deleteContactFormMessages(RequestWithIdOnly id) throws Exception {

        contactFormMessagesRepository.deleteContactFormMessages(id);
    }

    @Override
    public ContactFormMessagesResponse getContactFormMessageById(RequestWithIdOnly request) {

        return contactFormMessagesRepository.getContactFormMessageById(request);
    }
}
