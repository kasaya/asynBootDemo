package com.oycl.demo.service.impl;

import com.oycl.demo.service.ExampleService;
import com.oycl.demo.service.task.ExampleTask;
import com.oycl.demo.service.task.ExampleTask2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExampleServiceImpl implements ExampleService {
    @Autowired
    private ExampleTask exampleTask;

    @Autowired
    private ExampleTask2 exampleTask2;


    @Override
    public ExampleTask task1() {
        return exampleTask;
    }

    @Override
    public ExampleTask2 task2() {
        return exampleTask2;
    }
}
