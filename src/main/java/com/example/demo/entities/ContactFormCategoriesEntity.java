package com.example.demo.entities;


import org.springframework.lang.Nullable;

import javax.persistence.*;

@Entity
@Table(name = "contact_form_categories")
public class ContactFormCategoriesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "message_for_contact")
    private String typeOfCategory;

    public ContactFormCategoriesEntity() {
    }

    public ContactFormCategoriesEntity(String typeOfCategory) {
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
