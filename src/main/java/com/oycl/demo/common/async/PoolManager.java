package com.oycl.demo.common.async;

import com.oycl.demo.controller.ExampleController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.*;

/**
 * 线程池管理
 */
@Component
public class PoolManager {

    private Logger logger = LoggerFactory.getLogger(PoolManager.class);

    private ExecutorService cachedThreadPool;

    public ExecutorService getExecutor() {
        return cachedThreadPool;
    }

    @PostConstruct
    public void init(){
        logger.info("init executor");
        cachedThreadPool = Executors.newCachedThreadPool();

    }

    @PreDestroy
    public void destory(){
        logger.info("shutdown executor");
        cachedThreadPool.shutdown();
    }
}
