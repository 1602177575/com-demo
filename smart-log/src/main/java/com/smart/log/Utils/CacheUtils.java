package com.smart.log.Utils;

import com.smart.log.Action.Cache;
import com.smart.log.Action.MethodLogAction;


import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public final class CacheUtils {

    public static void getInfo(Class cla) throws NoSuchFieldException {

        Class userClass = cla.getClass();

        //获取当前类下注解中数据
        Annotation[] annotations = userClass.getAnnotations();
        for (Annotation annotation:annotations){
            System.out.println(annotation);
            Cache a=(Cache)annotation;
            if(a!=null){
                System.out.println(a.key());
                System.out.println(a.timeOut());
                System.out.println(a.timeUnit());
            }
        }

        //获取子注解中的值
        Field[] declaredFields = userClass.getDeclaredFields();
        for (Field declaredField:declaredFields){
            System.out.println(declaredField.getName());
            MethodLogAction annotation = declaredField.getAnnotation(MethodLogAction.class);
            if(annotation!=null){
                System.out.println(annotation.name());
                System.out.println(annotation.date());
            }

        }

    }

}
