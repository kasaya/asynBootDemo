package com.oycl.demo.controller;


import com.oycl.demo.common.async2.BlockingTaskManager;
import com.oycl.demo.common.async2.DeferredResultFactory;
import com.oycl.demo.service.ExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Map;


@RequestMapping("/test2")
@RestController
public class Example2Controller  {

    @Autowired
    ExampleService exampleService;

    @Autowired
    public BlockingTaskManager blockingTaskManager;


    @RequestMapping("/Task1")
    public DeferredResult<Object> search(String param) throws InterruptedException {

        final DeferredResult<Object> result = DeferredResultFactory.INSTENSE.createResult();

        blockingTaskManager.future(item->{
            result.setResult(exampleService.task3(param));
        });
        return result;
    }



    @RequestMapping("/Task3")
    public Map task3(String param){
        return exampleService.task3(param);
    }



}
