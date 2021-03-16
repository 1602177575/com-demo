package com.smar.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;


@SpringBootTest
@Slf4j
class IndexServiceTest {

    @Resource
    IndexService indexService;

    @Test
    void addListener() throws IOException {
        Boolean java = indexService.addListener("java");
        System.out.println(java);
    }
}