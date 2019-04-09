package com.oycl.demo.common.async;

import com.alibaba.ttl.TtlRunnable;
import com.oycl.demo.controller.ExampleController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

/**
 * 请求队列
 */
@Component
public class TaskManager<In, Out, IService extends RunnableService> {

    private Logger logger = LoggerFactory.getLogger(ExampleController.class);
    /**
     * 请求队列 缓冲容量50
     */
    private static final int QEQUE_SIZE = 50;

    private BlockingDeque<TaskInfo<In,Out,IService>> requesQueue = new LinkedBlockingDeque<>(QEQUE_SIZE);

    @Autowired
    private PoolManager poolManager;

    /**
     * 执行任务
     */
    public void doTask(){
        try {
            logger.info("执行 请求队列 开始");
            TaskInfo<In, Out, IService> vo = requesQueue.take();
            IService service =  vo.getService();
            service.setInfo(vo);
            Future future = poolManager.getExecutor().submit(TtlRunnable.get(service));
            try {
                if(future.get() != null){
                    //TODO：任务失败处理
                    logger.info("任务失败",((Exception)future.get()));
                }
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            logger.info("执行 请求队列 结束");
        } catch (InterruptedException e) {
            logger.error("requesQueue参数获取失败",e);
        }
    }

    /**
     * 将需要执行的对象放入任务队列
     * @param vo 任务对象
     * @throws InterruptedException
     */
    public void putTask(TaskInfo<In,Out,IService> vo) throws InterruptedException {
        this.requesQueue.put(vo);
    }
}
