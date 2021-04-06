package com.td.web.aspect;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @className: TimeAcpect
 * @description:
 * @author: cyd
 * @date: 2019/11/30 下午11:41
 **/
@Aspect
@Component
public class TimeAspect {
//    @Before() 相当拦截器perHandle，方法调用前
//    @After() 方法成功返回之后
//    @AfterThrowing 方法抛出异常
//    @Around() 覆盖上面3个方法

    @Around("execution(* com.td.web.controller.UserController.*(..))")
    public Object handleControllerMothed(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("time aspect start");
        Object[] args = proceedingJoinPoint.getArgs();
        for (Object arg : args) {
            System.out.println("arg is "+ arg);
        }

        long startTime = System.currentTimeMillis();
        Object object = proceedingJoinPoint.proceed();
        System.out.println("time interceptor 耗时： " +(System.currentTimeMillis() - startTime));
        System.out.println("time aspect end");
        return object;
    }
}
