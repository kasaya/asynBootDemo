package com.oycl.demo.common.async2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Consumer;

/**
 * 实际执行类
 */
public class Execution {

    private final static Logger LOGGER = LoggerFactory.getLogger(Execution.class);
    /**
     * 执行Consumer并将异常化解在内部.
     */
    public static final <T> boolean execute(TaskInfo<T> c) {
        try {
            c.getConsumer().accept(c.getParams());
            return true;
        } catch (Exception e) {
            //TODO: 异常处理
            if (LOGGER.isWarnEnabled()) {
                LOGGER.warn("{}", e);
            }
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("e: {}", e.getCause(), e.getStackTrace());
            }
            return false;
        }
    }

}
