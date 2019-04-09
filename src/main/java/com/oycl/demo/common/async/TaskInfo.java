package com.oycl.demo.common.async;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.context.request.async.DeferredResult;

/**
 *  异步处理信息
 * @param <I> 请求入参
 * @param <O> 返回出参
 * @param <IService> 处理此次请求的service引用
 */
@Getter
@Setter
public class TaskInfo<I,O,IService> {

    /**
     * 请求参数
     */
    private I params;

    /**
     * 响应结果
     */
    private DeferredResult<O> result;

    /**
     * 执行service
     */
    private IService service;


}
