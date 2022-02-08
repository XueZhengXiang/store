package com.project.store.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TimerAspect {
    @Around("execution(* com.project.store.service.impl.*.*(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        //调用目标方法login
        Object result = joinPoint.proceed();
        long end = System.currentTimeMillis();
        System.out.println("耗时:" + (end - start+"ms"));
        return result;

    }
}
