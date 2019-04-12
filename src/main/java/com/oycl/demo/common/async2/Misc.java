package com.oycl.demo.common.async2;

import java.util.function.Consumer;

public class Misc {
    /**
     * 执行Consumer并将异常化解在内部.
     */
    public static final <T> boolean exeConsumer(Consumer<T> c) {
        try {
            c.accept(null);
            return true;
        } catch (Exception e) {
//            if (logger.isWarnEnabled()) {
//                logger.warn("{}", Misc.trace(new Throwable()));
//            }
//            if (logger.isWarnEnabled()) {
//                logger.warn("t: {}, e: {}", t, Misc.trace(e));
//            }
            return false;
        }
    }

}
