package com.example.demo.repositories;

public interface MockRepository {
    void populateDB();
    void populateUsers();
    void populateCompanies();
    void populateCars();
    void populateStations();
    void populatePowerUnites();
    void populateRequests();
    void populateFormCategories();
    void populateMessages();
}
