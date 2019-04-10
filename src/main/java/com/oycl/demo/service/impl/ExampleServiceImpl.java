package com.oycl.demo.service.impl;

import com.oycl.demo.common.async.TaskInfo;
import com.oycl.demo.common.base.BusinessLogic;
import com.oycl.demo.orm.dao.MTaskMapper;
import com.oycl.demo.orm.model.MTask;
import com.oycl.demo.service.ExampleService;
import com.oycl.demo.service.task.ExampleTask1;
import com.oycl.demo.service.task.ExampleTask2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class ExampleServiceImpl implements ExampleService {

    @Autowired
    MTaskMapper mTaskMapper;

    @Override
    public void task1(TaskInfo info) {
        //事务提交测试：
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        MTask param = new MTask();
        param.setId(uuid);
        param.setName(Thread.currentThread().getName() + " "+  info.getParams());
        mTaskMapper.insert(param);


        Map<String, Object> map = new HashMap<>(10);
        map.put("params", info.getParams());
        map.put("time", System.currentTimeMillis());

        info.getResult().setResult(map);
    }

    @Override
    public void task2(TaskInfo info) {
        //事务提交测试：
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        MTask param = new MTask();
        param.setId(uuid);
        param.setName(info.getParams() + " " +Thread.currentThread().getName());
        mTaskMapper.insert(param);

        //模拟异常测试：
        int i = 1;
        int n = 0;
        int a = i/n;

        Map<String, Object> map = new HashMap<>(10);
        map.put("params", info.getParams());
        map.put("time", System.currentTimeMillis());

        info.getResult().setResult(map);
    }
}
