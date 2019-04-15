package com.oycl.demo.common.async2;

import org.springframework.stereotype.Component;

@Component
public class BlockingTaskManager extends BlockingTask {
    /**
     * 可以设置CPU*2的
     */
    private final static int THREAD_SIZE = 8;

    public BlockingTaskManager() {
        //设置线程数量
        this.setTc(THREAD_SIZE);
        //启动消费线程
        this.start();
    }
}

