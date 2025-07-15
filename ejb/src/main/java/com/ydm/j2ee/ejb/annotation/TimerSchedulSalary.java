package com.ydm.j2ee.ejb.annotation;

import jakarta.interceptor.InterceptorBinding;

import java.lang.annotation.*;

@Inherited
@InterceptorBinding
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface TimerSchedulSalary {
}
