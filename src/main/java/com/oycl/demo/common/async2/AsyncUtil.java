package com.oycl.demo.common.async2;

import java.util.function.Consumer;

public class AsyncUtil {

    private static BlockingTaskManager blockingTaskManager;


    public static void setBlockingTaskManager(BlockingTaskManager blockingTaskManager) {
        AsyncUtil.blockingTaskManager = blockingTaskManager;
    }

    public static <I> void run(Consumer<I> tConsumer, I param){
        TaskInfo<I> taskInfo = new TaskInfo<>();
        taskInfo.setParams(param);
        taskInfo.setConsumer(tConsumer);
        blockingTaskManager.push(taskInfo);
    }
}
