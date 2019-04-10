package com.oycl.demo.common.async;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.HashMap;
import java.util.Map;

/**
 * 统一生成 DeferredResult（工厂类）
 * @param <T> 返回值实体类型
 */
@Component
public class DeferredResultFactory<T>  {

    private static final long TIME_OUT = 1000L*60L;

    private static void run() {
        System.out.println(Thread.currentThread().getName() + "请求超时");
    }

    public DeferredResult<T> createResult(final Object result){
        DeferredResult<T> newItem = new DeferredResult<>(TIME_OUT,result);
        newItem.onTimeout(DeferredResultFactory::run);

        return newItem;
    }
    public DeferredResult<T> createResult(){
        //TODO: 定制超时返回信息
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("resultCode","503");
        resultMap.put("resultMsg","请求超时");
        return createResult(resultMap);
    }



}
