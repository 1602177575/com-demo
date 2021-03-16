package com.smart.api.utils;

import java.time.temporal.ValueRange;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

/**
 * 获取当前用户信息
 */


public class UserUtils {

    private static ThreadLocal<String> token = new ThreadLocal<>();


    public static String getTokenUserName(){
        return token.get();
    }


    public static void setTokenName(String name){
        token.set(name);
    }
}
