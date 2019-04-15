package com.oycl.demo.common.async2;

import org.springframework.stereotype.Component;

@Component
public class BlockingTaskManager extends BlockingTask {
    /**
     * 可以设置CPU*2的
     */
    private int threadSize = 10;

    public BlockingTaskManager() {
        //设置线程数量
        this.setTc(threadSize);
        //启动消费线程
        this.start();
    }
}

