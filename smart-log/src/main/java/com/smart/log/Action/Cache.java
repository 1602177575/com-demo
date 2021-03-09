package com.smart.log.Action;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Cache{

    /**
     * 大 key
     * @return
     */
    String key();

    /**
     * 过期时间
     * @return
     */
    int timeOut() default 10;

    /**
     * 过期时间单位
     * @return
     */
    TimeUnit timeUnit() default TimeUnit.SECONDS;


}
