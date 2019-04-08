package com.oycl.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

//@Component
public class TaskListener {

    @Autowired
    private RquestTask rquestTask;

    @PostConstruct
    public void init(){
        rquestTask.start();
    }

    @PreDestroy
    public void destory(){
        rquestTask.setRunning(false);
    }
}
