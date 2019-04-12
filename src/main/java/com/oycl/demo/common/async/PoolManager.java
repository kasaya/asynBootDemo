package com.oycl.demo.common.async;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolExecutorFactoryBean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.*;

/**
 * 线程池管理
 *
 * @author oycl
 */
@Component
public class PoolManager {

    private static final int  CORE_POOL_SIZE = 0;

    private static final long KEEP_LIVE_TIME = 60;

    private static final int MAX_POOL_SIZE = 1000;

    private Logger logger = LoggerFactory.getLogger(PoolManager.class);

    private ExecutorService cachedThreadPool;

    public ExecutorService getExecutor() {
        return cachedThreadPool;
    }

    @PostConstruct
    public void init() {
        logger.info("init executor");

        ThreadFactory threadFactory = new ThreadFactoryBuilder()
                .setNameFormat("service-pool-%d")
                .build();

        cachedThreadPool = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE,
                KEEP_LIVE_TIME, TimeUnit.SECONDS,
                new SynchronousQueue<>(), threadFactory,new ThreadPoolExecutor.CallerRunsPolicy());

    }

    @PreDestroy
    public void destory() {
        logger.info("shutdown executor");
        cachedThreadPool.shutdown();
    }
}
