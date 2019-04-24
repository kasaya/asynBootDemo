package com.oycl.demo.common.log.annotation;


import java.lang.annotation.*;

/**
 * 用于开启自定义Log
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Inherited
public @interface CustomLog {
}
