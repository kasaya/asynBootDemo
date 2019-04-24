package com.oycl.demo.common.log.process;


import com.google.gson.Gson;
import com.oycl.demo.common.log.manager.DefaultLogManager;
import com.oycl.demo.common.log.partner.ILogManager;
import com.oycl.demo.common.log.partner.LogModel;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * conrtoller, service, api  切面log 实现
 */
@Aspect
public class LogProcess {


    @Autowired
    private ILogManager logManager;

    @Autowired
    private Gson gson;



    @Pointcut("@within(com.oycl.demo.common.log.annotation.CustomLog)")
    public void wlogPoint(){}

    @Around("wlogPoint()")
    public Object aroundwLogPoint(ProceedingJoinPoint joinPoint) throws Throwable {

        //取得类
        Class target= joinPoint.getSignature().getDeclaringType();

        //取得调用的方法
        Method method = getInvokedMethod(joinPoint);
        if(Objects.isNull(method)){
            return joinPoint.proceed();
        }

        LogModel logModel = new LogModel();
        logModel.setOptContent(getParam(joinPoint));
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object result = joinPoint.proceed();
        stopWatch.stop();
        logModel.setCreateDate(stopWatch.getTotalTimeSeconds());
        logModel.setEvent(method.getName());
        logModel.setModule(target.getName());
        logModel.setStatus("完成");
        dealLog(logModel);

        return result;

    }

    /**
     * 获取请求方法
     * @param joinPoint
     * @return
     */
    private Method getInvokedMethod(JoinPoint joinPoint){
        MethodInvocationProceedingJoinPoint mjoinPoint = (MethodInvocationProceedingJoinPoint)joinPoint;
        Method method = ((MethodSignature)mjoinPoint.getSignature()).getMethod();
        return method;
    }

    /**
     * 取得全部参数
     * @param joinPoint
     * @return
     */
    private String getParam(JoinPoint joinPoint){
        return Arrays.stream(joinPoint.getArgs()).map(obj-> {
            if(obj instanceof HttpServletResponse){
                return "HttpServletResponse";
            }else if(obj instanceof HttpServletRequest){
                return "HttpServletRequest";
            }
            return gson.toJson(obj);
        }).collect(Collectors.joining(","));

    }

    /**
     * 处理Log
     * @param logModel
     */
    private void dealLog(LogModel logModel){
        if(logManager == null){
            logManager = DefaultLogManager.INSTANCE;
        }
        logManager.dealLog(logModel);
    }

}
