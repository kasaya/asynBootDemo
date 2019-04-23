package com.oycl.demo.common.async;

import com.alibaba.ttl.TtlRunnable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 并发任务执行管理器
 *
 * @author oycl
 */
@Deprecated
@Component
public class TaskManager {

    private Logger logger = LoggerFactory.getLogger(TaskManager.class);
    /**
     * 请求队列 缓冲容量100
     */
    private static final int QEQUE_SIZE = 1000;

    private BlockingQueue<TaskInfo<String, Object>> requesQueue = new LinkedBlockingQueue<>(QEQUE_SIZE);

    @Autowired
    private PoolManager poolManager;

    /**
     * 执行任务
     */
    public void doTask() {
        try {
            //logger.info("执行 请求队列 开始");
            TaskInfo<String, Object> vo = requesQueue.take();
            RunnableService service = vo.getService();
            service.setInfo(vo);
            poolManager.getExecutor().execute(TtlRunnable.get(service));
            //logger.info("执行 请求队列 结束");
        } catch (InterruptedException e) {
            logger.error("requesQueue参数获取失败", e);
        }
    }

    /**
     * 将需要执行的对象放入任务队列
     *
     * @param vo 任务对象
     * @throws InterruptedException
     */
    public void putTask(TaskInfo vo) throws InterruptedException {
        this.requesQueue.put(vo);
    }
}
