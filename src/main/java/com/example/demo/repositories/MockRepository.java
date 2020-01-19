package com.example.demo.repositories;

import java.sql.SQLIntegrityConstraintViolationException;

public interface MockRepository {
    void populateDB() throws SQLIntegrityConstraintViolationException;
}
