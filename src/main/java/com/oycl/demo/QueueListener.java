package com.oycl.demo;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Component
public class QueueListener {

    public ThreadPoolExecutor getExecutor() {
        return executor;
    }

    private ThreadPoolExecutor executor;
    @PostConstruct
    public void init(){
        System.out.println("init() executor");
        executor = new ThreadPoolExecutor(1, 10,3, TimeUnit.SECONDS, new SynchronousQueue<>(),new ThreadPoolExecutor.CallerRunsPolicy());
    }

    @PreDestroy
    public void destory(){
        System.out.println("shutdown");
        executor.shutdown();
    }
}
