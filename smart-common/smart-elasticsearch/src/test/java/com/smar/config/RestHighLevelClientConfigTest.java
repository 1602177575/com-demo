package com.smar.config;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;

@Slf4j
@SpringBootTest
class RestHighLevelClientConfigTest {

    @Resource
    private RestHighLevelClient restHighLevelClient;

    @Test
    void restHighLevelClient() throws IOException {
        GetIndexRequest request=new GetIndexRequest("user_index");
        boolean exists = restHighLevelClient.indices().exists(request, RequestOptions.DEFAULT);
        log.info("结果{}",exists);
    }
}