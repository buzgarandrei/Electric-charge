package com.example.demo.controllers;

import com.example.demo.services.MockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MockController {

    @Autowired
    private MockService mockService;

    @RequestMapping(path = "/alearga")
    public void trigger()  {
        mockService.run();
    }
}
