package com.example.demo.controllers;

import com.example.demo.services.MockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MockController {

    @Autowired
    MockService mockService;

    @RequestMapping(value = "/alearga",method = RequestMethod.POST)
    public void populate() {
        mockService.populate();
    }
}
