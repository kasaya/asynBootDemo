package com.oycl.demo.controller;

import com.oycl.demo.AnycVo;
import com.oycl.demo.RequestQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.stream.Collectors;

@RequestMapping("/test")
@RestController
public class SearchController {

    @Autowired
    private RequestQueue queue;

    @RequestMapping("/search")
    public DeferredResult<Object> testDeferredResult(String param) throws InterruptedException {
        System.out.println(Thread.currentThread().getName()+"请求开始");
        System.out.println(Thread.currentThread().getName()+"当前请求数："+queue.getRequesQueue().size());
        AnycVo<String, Object> vo = new AnycVo<>();

        //0L 永不超时
        //DeferredResult<Object> result = new DeferredResult<>(0L);
        DeferredResult<Object> result = new DeferredResult<>((1000L*60L));
        //result.setErrorResult(Thread.currentThread().getName()+"错误");
        result.onTimeout(()->{
            System.out.println(Thread.currentThread().getName()+"请求超时");
        });
        vo.setParams(param);
        vo.setResult(result);

        queue.getRequesQueue().put(vo);

        System.out.println(Thread.currentThread().getName()+"返回请求");

        return result;
    }

//    @ExceptionHandler
//    @ResponseBody
//    public String handleAsyncRequestTimeoutException(AsyncRequestTimeoutException e) {
//        System.out.println("请求超时");
//        return SseEmitter.event().data("timeout!!").build().stream()
//                .map(d -> d.getData().toString())
//                .collect(Collectors.joining());
//    }



}
