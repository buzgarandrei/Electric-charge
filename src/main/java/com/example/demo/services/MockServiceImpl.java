package com.example.demo.services;

import com.example.demo.repositories.MockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MockServiceImpl implements MockService {

    @Autowired
    private MockRepository mockRepository;

    @Override
    public void run() {
        mockRepository.populateDB();
    }
}
