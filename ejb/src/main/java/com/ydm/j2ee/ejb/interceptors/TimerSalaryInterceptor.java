package com.ydm.j2ee.ejb.interceptors;


import com.ydm.j2ee.ejb.annotation.TimerSchedulSalary2;
import jakarta.annotation.Priority;
import jakarta.ejb.Timer;
import jakarta.interceptor.AroundTimeout;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;

@TimerSchedulSalary2
@Interceptor
@Priority(1)
public class TimerSalaryInterceptor {

    @AroundTimeout
    public Object aroundTimeOut(InvocationContext invocationContext) throws Throwable {
        System.out.println("aroundTimeOut");

        Timer timer =  (Timer) invocationContext.getTimer();
        timer.cancel();

        return invocationContext.proceed();
    }
}
