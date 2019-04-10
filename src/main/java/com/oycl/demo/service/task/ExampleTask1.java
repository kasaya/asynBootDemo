package com.oycl.demo.service.task;

import com.oycl.demo.common.async.RunnableService;
import com.oycl.demo.orm.dao.MTaskMapper;
import com.oycl.demo.orm.model.MTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class ExampleTask1 extends RunnableService {

    @Autowired
    MTaskMapper mTaskMapper;

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    @Override
    public void run() {
        //事务提交测试：
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        MTask param = new MTask();
        param.setId(uuid);
        param.setName(Thread.currentThread().getName() + " "+  this.getInfo().getParams());
        mTaskMapper.insert(param);


        Map<String, Object> map = new HashMap<>(10);
        map.put("params", this.getInfo().getParams());
        map.put("time", System.currentTimeMillis());

        this.getInfo().getResult().setResult(map);
        this.removeInfo();
    }
}
