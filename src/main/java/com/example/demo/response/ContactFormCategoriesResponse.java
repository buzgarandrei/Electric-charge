package com.example.demo.response;

import com.example.demo.entities.ContactFormMessagesEntity;

public class ContactFormCategoriesResponse {

    private Long id;
    private String typeOfCategory;

    public ContactFormCategoriesResponse() {
    }

    public ContactFormCategoriesResponse(Long id, String typeOfCategory) {
        this.id = id;
        this.typeOfCategory = typeOfCategory;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeOfCategory() {
        return typeOfCategory;
    }

    public void setTypeOfCategory(String typeOfCategory) {
        this.typeOfCategory = typeOfCategory;
    }
}
