package com.oycl.demo.common.async;

import com.alibaba.ttl.TtlCallable;
import com.alibaba.ttl.TtlRunnable;
import com.oycl.demo.controller.ExampleController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

/**
 * 请求队列
 */
@Component
public class TaskManager<In, Out> {

    private Logger logger = LoggerFactory.getLogger(ExampleController.class);
    /**
     * 请求队列 缓冲容量50
     */
    private static final int QEQUE_SIZE = 50;

    private BlockingDeque<TaskInfo<In,Out>> requesQueue = new LinkedBlockingDeque<>(QEQUE_SIZE);

    @Autowired
    private PoolManager poolManager;

    /**
     * 执行任务
     */
    public void doTask(){
        try {
            logger.info("执行 请求队列 开始");
            TaskInfo<In, Out> vo = requesQueue.take();
            RunnableService service =  vo.getService();
            service.setInfo(vo);
            Future future = poolManager.getExecutor().submit(TtlRunnable.get(service));
            try {
                //捕捉执行异常
                future.get();
            } catch (ExecutionException e) {
                DeferredResult<Out>  outDeferredResult = vo.getResult();
                Map<String, String> resultMap = new HashMap<>();
                resultMap.put("resultCode","204");
                resultMap.put("resultMsg","任务失败");
                outDeferredResult.setErrorResult(resultMap);
                logger.info("任务失败",e);
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
    public void putTask(TaskInfo<In,Out> vo) throws InterruptedException {
        this.requesQueue.put(vo);
    }
}
