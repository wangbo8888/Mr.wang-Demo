package com.example.demo.annotation;

import java.lang.annotation.*;

/**
 * 自定义注解
 * @author Mr.wang
 */
@Target({ElementType.FIELD,ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyAnnotation {
    String color() default "1";
    String value() default "value";
    boolean off() default  true;
    String[] list() default {};
}
