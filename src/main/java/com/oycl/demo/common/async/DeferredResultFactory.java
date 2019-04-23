package com.oycl.demo.common.async;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.HashMap;
import java.util.Map;

/**
 * 统一生成 DeferredResult（工厂类）
 *
 * @author oycl
 */
@Deprecated
@Component
public class DeferredResultFactory {

    private static final long TIME_OUT = 1000L*60L;

    private static void run() {
        System.out.println(Thread.currentThread().getName() + "请求超时");
    }

    private <T> DeferredResult<T> createResult(final T result){
        DeferredResult<T> newItem;
        if(result != null){
            newItem = new DeferredResult<>(TIME_OUT,result);
        }else{
            newItem = new DeferredResult<>(TIME_OUT);
        }
        newItem.onTimeout(DeferredResultFactory::run);


        return newItem;
    }

    public DeferredResult createResult(){
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("resultCode","503");
        resultMap.put("resultMsg","请求超时");
        return createResult(resultMap);
    }

}
