package com.oycl.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class RquestTask extends Thread {

    @Autowired
    private RequestQueue queue;

    private boolean running = true;

    @Override
    public void run(){
        while (running){
            try{
                AnycVo<String,Object> vo = queue.getRequesQueue().take();
                //TODO:处理

                String param = vo.getParams();
                System.out.println(param+"处理开始");
                Thread.sleep(1500);

                Map<String, Object> map = new HashMap<>(10);
                map.put("params",param);
                map.put("time",System.currentTimeMillis());

                vo.getResult().setResult(map);

                System.out.println(param+"处理完成");
            }catch (InterruptedException e){
                running = false;
            }
        }
    }
    public void setRunning(boolean running){
        this.running = running;
    }
}
