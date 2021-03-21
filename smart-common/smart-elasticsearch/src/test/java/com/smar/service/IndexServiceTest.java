package com.smar.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.Map;


@SpringBootTest
@Slf4j
class IndexServiceTest {

    @Resource
    IndexService indexService;

    @Test
    void addListener() throws IOException {
        List<Map<String, Object>> java = indexService.Highlight("java", 0, 100);
        System.out.println(java);
    }
}