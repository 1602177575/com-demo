package com.smart.redis.service;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import sun.security.pkcs11.wrapper.CK_SSL3_RANDOM_DATA;

import javax.annotation.Resource;


@SpringBootTest
public class RedisServiceTest {
    @Resource(name = "redisService")
    RedisService redisService;


    @Test
    public void set() {

    }

    @Test
    public void get() {
        Object user = redisService.get("user");
    }
}