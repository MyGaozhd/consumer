package com.servi.cloud.consumer.spring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect
public class LogAspects {

    @Pointcut(" execution(public int com.servi.cloud.consumer.spring.aop.Math.*(..))")
    public void pointCut() {
    }

    @Before("pointCut()")
    public void logStart(JoinPoint joinPoint) {
        System.out.println("开始:{}" + joinPoint.getArgs());
    }

    @After("pointCut()")
    public void logEnd() {
        System.out.println("结束");
    }

    @AfterReturning(value = "pointCut()", returning = "r")
    public void logReturn(Object r) {
        System.out.println("返回值:{}" + r);
    }

    @AfterThrowing("pointCut()")
    public void logError() {
        System.out.println("异常");
    }

    public void logAround() {
        System.out.println("环绕:{}");
    }
}
