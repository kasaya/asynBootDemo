package com.oycl.demo.service.impl;

import com.oycl.demo.service.ExampleService;
import com.oycl.demo.service.task.ExampleTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExampleServiceImpl implements ExampleService {
    @Autowired
    private ExampleTask exampleTask;


    @Override
    public ExampleTask task1() {
        return exampleTask;
    }

    @Override
    public ExampleTask task2() {
        return exampleTask;
    }
}
