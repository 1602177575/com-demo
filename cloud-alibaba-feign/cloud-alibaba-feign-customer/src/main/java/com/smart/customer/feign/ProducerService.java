package com.smart.customer.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

// 使用服务名称代理
//context-path + reqeustMapping()
// http://10.3.133.159:8088/pro/
@FeignClient(value = "cloud-feign-producer", path = "/api/pro")
public interface ProducerService {
    @GetMapping("/")
    String test(int uid);
}
