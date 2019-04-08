package com.oycl.demo.service;

import com.oycl.demo.AnycVo;
import com.oycl.demo.base.BaseService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class Myservice<In, Out> extends BaseService<String, Map> {
    /**
     * 请求参数
     */
    protected ThreadLocal<AnycVo<In, Out>> info = new ThreadLocal<>();

    public AnycVo<In, Out> getInfo() {
        AnycVo<In, Out> result =  info.get();
        info.remove();
        return result;

    }

    public void setInfo(AnycVo<In, Out> info) {
        this.info.set(info);

    }

    @Override
    public void run() {
       System.out.println(this.info);
        Map<String, Object> map = new HashMap<>(10);
        map.put("params",this.info.get().getParams());
        map.put("time",System.currentTimeMillis());
        this.info.get().getResult().setResult(map);
        System.out.println(Thread.currentThread().getName()+ "success");
    }
}
