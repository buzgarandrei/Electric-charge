package com.example.demo.request;

public class ContactFormMessagesRequest {

    private Long id;
    private String messageOfContactForm;
    private Long idContactFormCategoriesEntity;
    private int state;

    public ContactFormMessagesRequest() {
    }

    public ContactFormMessagesRequest(Long id, String messageOfContactForm, Long idContactFormCategoriesEntity, int state) {
        this.id = id;
        this.messageOfContactForm = messageOfContactForm;
        this.idContactFormCategoriesEntity = idContactFormCategoriesEntity;
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

    public Long getIdContactFormCategoriesEntity() {
        return idContactFormCategoriesEntity;
    }

    public void setIdContactFormCategoriesEntity(Long idContactFormCategoriesEntity) {
        this.idContactFormCategoriesEntity = idContactFormCategoriesEntity;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
