package com.example.demo.repositories;

import com.example.demo.entities.ContactFormCategoriesEntity;
import com.example.demo.entities.ContactFormMessagesEntity;
import com.example.demo.request.ContactFormMessagesRequest;
import com.example.demo.request.specialRequests.RequestWithIdOnly;
import com.example.demo.response.ContactFormMessagesResponse;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class ContactFormMessagesRepositoryImpl implements ContactFormMessagesRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public List<ContactFormMessagesEntity> findAll() {

        Query query = entityManager.createQuery("select cfm from ContactFormMessagesEntity cfm",ContactFormMessagesEntity.class);
        List<ContactFormMessagesEntity> resultList = query.getResultList();

        return resultList;

    }

    @Override
    @Transactional
    public void addContactFormMessages(ContactFormMessagesRequest contactFormMessagesRequest) throws Exception {


        ContactFormMessagesEntity entity = new ContactFormMessagesEntity();
       try {
           ContactFormCategoriesEntity contact = entityManager.find(ContactFormCategoriesEntity.class, contactFormMessagesRequest.getIdContactFormCategoriesEntity());
           entity.setContactFormCategoriesEntity(contact);
       }
       catch (NullPointerException e) {
           e.printStackTrace();
           System.out.println("Bad id");
       }
        entity.setState(contactFormMessagesRequest.getState());
        entity.setMessageOfContactForm(contactFormMessagesRequest.getMessageOfContactForm());

        entityManager.persist(entity);


    }

    @Override
    @Transactional
    public void updateContactFormMessages(ContactFormMessagesRequest contactFormMessagesRequest) throws Exception {

        try {
            ContactFormMessagesEntity contactFormMessagesEntity = entityManager.find(ContactFormMessagesEntity.class, contactFormMessagesRequest.getId());

            if(contactFormMessagesRequest.getMessageOfContactForm() != null)
                contactFormMessagesEntity.setMessageOfContactForm(contactFormMessagesRequest.getMessageOfContactForm());

            if(contactFormMessagesRequest.getState() != 0)
                contactFormMessagesEntity.setState(contactFormMessagesRequest.getState());

            if(contactFormMessagesRequest.getIdContactFormCategoriesEntity() != null) {
                ContactFormCategoriesEntity contactFormCategoriesEntity = entityManager.find(ContactFormCategoriesEntity.class, contactFormMessagesRequest.getIdContactFormCategoriesEntity());
                contactFormMessagesEntity.setContactFormCategoriesEntity(contactFormCategoriesEntity);
            }

            entityManager.merge(contactFormMessagesEntity);
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("Bad Contact Form Category id most likely, or other fields");
        }
    }

    @Override
    @Transactional
    public void deleteContactFormMessages(RequestWithIdOnly id) throws Exception {

        try {
            ContactFormMessagesEntity entity = entityManager.find(ContactFormMessagesEntity.class,id.getId());
            entityManager.remove(entity);
        }
        catch (Exception e) {
            System.out.println("bad message id given");
        }

    }

    @Override
    @Transactional(readOnly = true)
    public ContactFormMessagesResponse getContactFormMessageById(RequestWithIdOnly request) {

        ContactFormMessagesEntity entity = entityManager.find(ContactFormMessagesEntity.class,request.getId());
        ContactFormMessagesResponse response = new ContactFormMessagesResponse();

        response.setId(entity.getId());
        response.setState(entity.getState());
        response.setMessageOfContactForm(entity.getMessageOfContactForm());
        response.setIdContactFormCategoriesEntity(entity.getContactFormCategoriesEntity().getId());

        return response;
    }
}
