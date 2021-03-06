package com.oycl.demo.controller;


import com.oycl.demo.common.async2.AsyncUtil;
import com.oycl.demo.common.async2.BlockingTaskManager;
import com.oycl.demo.common.async2.DeferredResultFactory;
import com.oycl.demo.common.log.annotation.CustomLog;
import com.oycl.demo.service.ExampleService;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Map;


@RequestMapping("/test2")
@RestController
@CustomLog
public class Example2Controller  {

    @Autowired
    ExampleService exampleService;


    @RequestMapping("/Task1")
    public DeferredResult<Object> search(String param) throws InterruptedException {

        final DeferredResult<Object> result = DeferredResultFactory.INSTENSE.createResult();

        AsyncUtil.run(item->{
            result.setResult(exampleService.task3(item));
        }, param);
        return result;
    }



    @RequestMapping("/Task3")
    public Map task3(String param){
        return exampleService.task3(param);
    }



}
