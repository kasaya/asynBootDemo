package com.oycl.demo.common.async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.async.DeferredResult;
@Deprecated
public class AsyncController {

    @Autowired
    private TaskManager taskManager;

    @Autowired
    private DeferredResultFactory factory;

    protected  <Out, IN> DeferredResult<Out> doBusinessLogic(IN param, IBusinessLogic logic) throws InterruptedException{
        //System.out.println(Thread.currentThread().getName()+"请求开始");
        DeferredResult<Out> result = factory.createResult();

        TaskInfo<IN, Out> vo = new TaskInfo<>();
        vo.setParams(param);
        vo.setResult(result);
        vo.setService(new RunnableService(logic));
        //放入队列
        taskManager.putTask(vo);
        //执行处理
        taskManager.doTask();
        //System.out.println(Thread.currentThread().getName()+"返回请求");
        return result;
    }
}
