package com.example.demo.services;

import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;


public interface MockService {
    void run() throws SQLIntegrityConstraintViolationException;
}
