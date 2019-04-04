package com.oycl.demo;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 请求队列
 */
@Getter
@Component
public class RequestQueue {
    /**
     * 请求队列 缓冲容量50
     * */
    private BlockingDeque<AnycVo<String,Object>> requesQueue = new LinkedBlockingDeque<>(50);
}
