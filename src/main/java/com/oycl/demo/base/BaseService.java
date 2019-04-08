package com.oycl.demo.base;

import com.oycl.demo.AnycVo;

public abstract class BaseService<In, Out> implements Runnable{
//    /**
//     *
//     * 请求参数
//     */
//    protected AnycVo<In, Out> info;
//
//    public AnycVo<In, Out> getInfo() {
//        return info;
//    }
//
//    public void setInfo(AnycVo<In, Out> info) {
//        this.info = info;
//    }

//    /**
//     * 请求参数
//     */
//    protected ThreadLocal<AnycVo<In, Out>> info = new ThreadLocal<>();
//
//    public AnycVo<In, Out> getInfo() {
//        AnycVo<In, Out> result =  info.get();
//        info.remove();
//        return result;
//
//    }
//
//    public void setInfo(AnycVo<In, Out> info) {
//        this.info.set(info);
//
//    }
}
