package com.oycl.demo.common.async;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.oycl.demo.common.base.BusinessLogic;

public abstract class RunnableService implements Runnable{

    /**
     *
     * 请求参数
     */
    private static final TransmittableThreadLocal<TaskInfo> TASK_INFO = new TransmittableThreadLocal<>();

    /**
     * 取得参数
     * @return
     */
    public TaskInfo getInfo() {
        return TASK_INFO.get();
    }

    /**
     * 设置
     * @param info
     */
    public void setInfo(TaskInfo info) {
        TASK_INFO.set(info);
    }

    /**
     *移除
     */
    public void removeInfo(){
        TASK_INFO.remove();
    }


}
