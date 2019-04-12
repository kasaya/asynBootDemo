package com.oycl.demo.controller;

import com.oycl.demo.common.async.AsyncController;
import com.oycl.demo.service.ExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Map;

@RequestMapping("/test")
@RestController
public class ExampleController extends AsyncController {

    @Autowired
    ExampleService exampleService;


    @RequestMapping("/Task1")
    public DeferredResult<Object> search(String param) throws InterruptedException {
        return doBusinessLogic(param,info->exampleService.task1(info));
    }

    @RequestMapping("/Task2")
    public DeferredResult<Object> testDeferredResult(String param) throws InterruptedException {

        return doBusinessLogic(param,info->exampleService.task2(info));
    }

    @RequestMapping("/Task3")
    public Map task3(String param){
        return exampleService.task3(param);
    }



}
