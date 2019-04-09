package com.oycl.demo.common.async;

import com.alibaba.ttl.TransmittableThreadLocal;

public abstract class RunnableService implements Runnable{
    /**
     *
     * 请求参数
     */
    private static final TransmittableThreadLocal<TaskInfo> ANYC_VO_THREAD_LOCAL = new TransmittableThreadLocal<>();

    /**
     * 取得参数
     * @return
     */
    public TaskInfo getInfo() {
        return ANYC_VO_THREAD_LOCAL.get();
    }

    /**
     * 设置
     * @param info
     */
    public void setInfo(TaskInfo info) {
        ANYC_VO_THREAD_LOCAL.set(info);
    }

    /**
     *移除
     */
    public void removeInfo(){
        ANYC_VO_THREAD_LOCAL.remove();
    }



}
