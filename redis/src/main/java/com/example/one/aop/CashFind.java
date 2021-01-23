package com.example.one.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解
 */
@Target(ElementType.METHOD)   //  该注解对方法有效
@Retention(RetentionPolicy.RUNTIME)  // 表示运行时有效
public @interface CashFind {
    //用户可以自己指定key，没指定的话为空字符串
    public String key() default "";
    //用户可以指定存在redis里面的过期时间，没指定的话默认为0
    public int seconds() default 0;
}
