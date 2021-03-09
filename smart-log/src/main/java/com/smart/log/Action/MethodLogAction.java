package com.smart.log.Action;

import java.lang.annotation.*;

@Target({ElementType.TYPE,ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface MethodLogAction {

    /**
     * 接口名称
     * @return
     */
    public String name() default "no Url";

    /**
     * 接口描述
     * @return
     */
    public String date() default "no Description";

}
