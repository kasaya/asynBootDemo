package com.oycl.demo.controller;

import com.oycl.demo.common.async.DeferredResultFactory;
import com.oycl.demo.common.async.TaskInfo;
import com.oycl.demo.common.async2.BlockingTask;
import com.oycl.demo.common.async2.BlockingTaskManager;
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
    public BlockingTaskManager appBlocking;

    @Autowired
    private DeferredResultFactory factory;

    @RequestMapping("/Task1")
    public DeferredResult<Object> search(String param) throws InterruptedException {

        final DeferredResult<Object> result = factory.createResult();

//        TaskInfo<String,Object> taskInfo = new TaskInfo();
//        taskInfo.setResult(result);
//        taskInfo.setParams(param);

        appBlocking.future(item->{
            result.setResult(exampleService.task3(param));
        });
        return result;
    }



    @RequestMapping("/Task3")
    public Map task3(String param){
        return exampleService.task3(param);
    }



}
