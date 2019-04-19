package com.oycl.demo.common.async2.annotation;


import com.oycl.demo.common.async2.AsyncTaskConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 开启异步service处理逻辑
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(AsyncTaskConfig.class)
public @interface EnableAsyncService {
}
