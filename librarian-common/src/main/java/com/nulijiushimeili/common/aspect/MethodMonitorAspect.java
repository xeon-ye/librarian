package com.nulijiushimeili.common.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;


/**
 * 监控方法的入参和执行结果
 */
@Aspect
@Component
@Slf4j
public class MethodMonitorAspect {


    @Pointcut(value = "@annotation(com.nulijiushimeili.common.annotation.Monitor)")
    public void ioMonitor(){}

    @Around(value = "ioMonitor()")
    public Object doMonitor(ProceedingJoinPoint joinPoint){

        // 获取监控方法的入参
        Object [] args = joinPoint.getArgs();
        // 获取方法执行的返回值
        Object returnValue = null;


        // 当前执行的方法的类名
        String className = joinPoint.getTarget().getClass().getSimpleName();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        // 当前执行的方法的方法名
        String methodName = signature.getMethod().getName();
        StringBuilder sb = new StringBuilder();
        sb.append("执行方法：").append( className).append(".").append(methodName).append("(") ;
        for (Object arg :  args) {
            sb.append(arg.toString());
            sb.append(",");
        }
        String methodInfo = sb.toString().substring(0,sb.toString().length()-1);
        methodInfo += ")";

        log.info(methodInfo);

        try {
            returnValue = joinPoint.proceed(args);
        } catch (Throwable throwable) {
            log.error("执行中出现了异常！");
            throwable.printStackTrace();
        }
        log.info("执行结果：" + returnValue.toString());

        return returnValue;
    }


}
