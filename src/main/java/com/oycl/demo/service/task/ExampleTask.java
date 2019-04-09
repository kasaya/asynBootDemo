package com.oycl.demo.service.task;

import com.oycl.demo.common.async.RunnableService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
public class ExampleTask extends RunnableService {

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    @Override
    public void run() {
            Map<String, Object> map = new HashMap<>(10);
            map.put("params",this.getInfo().getParams());
            map.put("time",System.currentTimeMillis());

            this.getInfo().getResult().setResult(map);
            this.removeInfo();
    }
}
