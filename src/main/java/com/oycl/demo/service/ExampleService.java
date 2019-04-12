package com.oycl.demo.service;

import com.oycl.demo.common.async.TaskInfo;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Map;

public interface ExampleService {

    void task1(TaskInfo info);

    void task2(TaskInfo info);

    Map task3(String params);

}
