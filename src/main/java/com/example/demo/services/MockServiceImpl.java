package com.example.demo.services;

import com.example.demo.repositories.MockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MockServiceImpl implements MockService {

    @Autowired
    MockRepository mockRepository;

    public void populate() {
        mockRepository.populate();
    }
}
