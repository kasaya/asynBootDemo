package com.oycl.demo.controller;

import com.oycl.demo.common.async.DeferredResultFactory;
import com.oycl.demo.common.async.RunnableService;
import com.oycl.demo.common.async.TaskManager;
import com.oycl.demo.common.async.TaskInfo;
import com.oycl.demo.common.base.BusinessLogic;
import com.oycl.demo.service.ExampleService;
import com.oycl.demo.service.task.ExampleTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

@RequestMapping("/test")
@RestController
public class ExampleController {

    @Autowired
    ExampleService exampleService;

    @Autowired
    private TaskManager<String, Object> taskManager;

    @Autowired
    private DeferredResultFactory factory;

    @RequestMapping("/Task1")
    public DeferredResult<Object> search(String param) throws InterruptedException {

        System.out.println(Thread.currentThread().getName()+"请求开始");

        DeferredResult<Object> result = factory.createResult();

        TaskInfo<String, Object> vo = new TaskInfo<>();
        vo.setParams(param);
        vo.setResult(result);
        vo.setService(new ExampleTask(info->exampleService.task1(info)));
        //放入队列
        taskManager.putTask(vo);
        //执行处理
        taskManager.doTask();
        System.out.println(Thread.currentThread().getName()+"返回请求");
        return result;
    }

    @RequestMapping("/Task2")
    public DeferredResult<Object> testDeferredResult(String param) throws InterruptedException {

        System.out.println(Thread.currentThread().getName()+"请求开始");

        DeferredResult<Object> result = factory.createResult();

        TaskInfo<String, Object> vo = new TaskInfo<>();
        vo.setParams(param);
        vo.setResult(result);
        vo.setService(new ExampleTask(info ->exampleService.task2(info)));
        //放入队列
        taskManager.putTask(vo);
        //执行处理
        taskManager.doTask();
        System.out.println(Thread.currentThread().getName()+"返回请求");
        return result;
    }



}
