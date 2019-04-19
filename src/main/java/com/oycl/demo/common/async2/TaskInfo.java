package com.oycl.demo.common.async2;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.function.Consumer;

/**
 * 异步处理信息载体
 *
 * @param <I> 请求入参
 * @author oycl
 */
@Getter
@Setter
public class TaskInfo<I> {

    /**
     * 请求参数
     */
    private I params;

    /**
     * 响应结果
     */
    private Consumer<I> consumer;



}
