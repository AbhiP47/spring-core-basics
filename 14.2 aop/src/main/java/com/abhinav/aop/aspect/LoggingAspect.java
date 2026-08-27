package com.abhinav.aop.aspect;

import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

    @Before("execution(* com.abhinav.aop.service.StudentService.*(..))")
    public void logBefore()
    {
        System.out.println("Method intercepted");
    }
}
