package com.oycl.demo.service;

import com.oycl.demo.common.async.TaskInfo;
import com.oycl.demo.service.task.ExampleTask1;
import com.oycl.demo.service.task.ExampleTask2;

public interface ExampleService {

    void task1(TaskInfo info);

    void  task2(TaskInfo info);
}
