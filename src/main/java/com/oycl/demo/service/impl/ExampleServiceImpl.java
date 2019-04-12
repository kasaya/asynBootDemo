package com.oycl.demo.service.impl;

import com.oycl.demo.common.async.TaskInfo;
import com.oycl.demo.orm.dao.MTaskMapper;
import com.oycl.demo.orm.model.MTask;
import com.oycl.demo.service.ExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class ExampleServiceImpl implements ExampleService {

    @Autowired
    MTaskMapper mTaskMapper;

    //@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void task1(TaskInfo info) {
        //事务提交测试：
//        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
//        MTask param = new MTask();
//        param.setId(uuid);
//        param.setName(Thread.currentThread().getName() + " "+  info.getParams());
//        mTaskMapper.insert(param);
        int count = mTaskMapper.select();
//        try {
//            Thread.sleep(1500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        Map<String, Object> map = new HashMap<>(10);
        map.put("params", count);
        map.put("time", System.currentTimeMillis());

        info.getResult().setResult(map);
    }

    //@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    @Override
    public void task2(TaskInfo info) {
        //事务提交测试：
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        MTask param = new MTask();
        param.setId(uuid);
        param.setName(info.getParams() + " " +Thread.currentThread().getName());
        //mTaskMapper.insert(param);

        //模拟异常测试：
        int i = 1;
        int n = 0;
        int a = i/n;

        Map<String, Object> map = new HashMap<>(10);
        map.put("params", info.getParams());
        map.put("time", System.currentTimeMillis());

        info.getResult().setResult(map);
    }

    @Override
    public Map task3(String params) {
//        //事务提交测试：
//        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
//        MTask param = new MTask();
//        param.setId(uuid);
//        param.setName(params + " " +Thread.currentThread().getName());
//        mTaskMapper.insert(param);
//        try {
//            Thread.sleep(1500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        int count = mTaskMapper.select();
        Map<String, Object> map = new HashMap<>(10);
        map.put("params", count);
        map.put("time", System.currentTimeMillis());
        return map;
    }


}
