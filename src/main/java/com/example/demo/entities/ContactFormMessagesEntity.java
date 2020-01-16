package com.example.demo.entities;

import javax.persistence.*;

//-pk id int
//-fk id int (Users)
//-message str
//-category int
//-state int ##0 - unread, 1 - read, 2 - solved
@Entity
@Table(name = "contact_form_messages")
public class ContactFormMessagesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "message",length = 2000)
    private String messageOfContactForm;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_category")
    private ContactFormCategoriesEntity contactFormCategoriesEntity;

    @Column(name = "state")
    private int state;

    public ContactFormMessagesEntity() {
    }

    public ContactFormMessagesEntity(String messageOfContactForm, ContactFormCategoriesEntity contactFormCategoriesEntity, int state) {
        this.messageOfContactForm = messageOfContactForm;
        this.contactFormCategoriesEntity = contactFormCategoriesEntity;
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessageOfContactForm() {
        return messageOfContactForm;
    }

    public void setMessageOfContactForm(String messageOfContactForm) {
        this.messageOfContactForm = messageOfContactForm;
    }

    public ContactFormCategoriesEntity getContactFormCategoriesEntity() {
        return contactFormCategoriesEntity;
    }

    public void setContactFormCategoriesEntity(ContactFormCategoriesEntity contactFormCategoriesEntity) {
        this.contactFormCategoriesEntity = contactFormCategoriesEntity;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
