package com.example.demo.request;

public class ContactFormCategoriesRequest {

    private Long id;
    private String typeOfCategory;

    public ContactFormCategoriesRequest() {
    }

    public ContactFormCategoriesRequest(Long id, String typeOfCategory) {
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
