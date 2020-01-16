package com.example.demo.controllers;

import com.example.demo.services.MockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLIntegrityConstraintViolationException;

@Controller
public class MockController {

    @Autowired
    private MockService mockService;

    @RequestMapping(path = "/alearga")
    public void trigger() throws SQLIntegrityConstraintViolationException {
        mockService.run();
    }
}
