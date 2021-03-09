package com.smart.log.Action;


import com.smart.log.Utils.CacheUtils;
import com.smart.log.pojo.TestLog;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class Action {

    @Test
    public void test() throws NoSuchFieldException {
        Class<TestLog> testLogClass = TestLog.class;
        Field[] fields = testLogClass.getFields();
        for (Field field:fields){
            System.out.println(field.getName());
        }

        //获取字段
        Field name = testLogClass.getField("name");
        System.out.println(name.getType().getName());

    }

    @Test
    public void test2() throws IllegalAccessException {
        TestLog testLog = new TestLog();
        Class<TestLog> testLogClass = TestLog.class;
        Field[] declaredFields = testLogClass.getDeclaredFields();
        for (Field declaredField:declaredFields){
            MethodLogAction annotation = declaredField.getAnnotation(MethodLogAction.class);
            if(annotation!=null){
                String name = annotation.name();
                System.out.println(name);
                declaredField.setAccessible(true);
                declaredField.set(testLog,name);
                String date = annotation.date();
                System.out.println(date);
                declaredField.set(testLog,date);
            }
        }
        System.out.println(testLog.toString());
    }

    public static void main(String[] args) throws Exception {

        Class userClass = User.class;
        //获取当前类下注解中数据
        Annotation[] annotations = userClass.getAnnotations();
        for (Annotation annotation:annotations){
            System.out.println(annotation);
            Cache a=(Cache)annotation;
            System.out.println(a.key());
            System.out.println(a.timeOut());
            System.out.println(a.timeUnit());
        }

        //获取子注解中的值
        Field[] declaredFields = userClass.getDeclaredFields();
        for (Field declaredField:declaredFields){
            System.out.println(declaredField.getName());
            MethodLogAction annotation = declaredField.getAnnotation(MethodLogAction.class);
            System.out.println(annotation.name());
            System.out.println(annotation.date());
        }
    }
}






@Cache(key = "测试")
class User{

    @MethodLogAction(name = "姓名",date = "666时间")
    public String name;

    @MethodLogAction(name = "年龄")
    public int age;

    @MethodLogAction(name = "密码",date = "String")
    public String password;

    @MethodLogAction
    private String address;

}