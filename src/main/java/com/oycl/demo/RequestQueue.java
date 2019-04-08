package com.oycl.demo;

import com.oycl.demo.base.BaseService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

/**
 * 请求队列
 */
@Component
public class RequestQueue<In, Out, IService extends BaseService> {
    /**
     * 请求队列 缓冲容量5000
     * */
    private BlockingDeque<AnycVo<In,Out>> requesQueue = new LinkedBlockingDeque<>(50);

    @Autowired
    private QueueListener queueListener;

    @Autowired
    IService service;

    public void doTask(){
        try {
            System.out.println("执行 请求队列 开始");
            System.out.println(Thread.currentThread().getName()+"当前请求数："+requesQueue.size());
            AnycVo<In, Out> vo = requesQueue.take();

            service.setInfo(vo);
            queueListener.getExecutor().execute(service);
            System.out.println("dan"+ queueListener.getExecutor().getActiveCount());
            System.out.println("执行 请求队列 结束");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void putVo(AnycVo<In,Out> vo) throws InterruptedException {
        this.requesQueue.put(vo);
    }
}
