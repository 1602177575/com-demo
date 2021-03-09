package com.smart.customer.feign;


import com.smart.customer.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author mtl
 */ // 使用服务名称代理
@FeignClient(value = "cloud-feign-producer", path = "/api/pro",fallback = ProducerServiceFallback.class)
public interface ProducerService {

    @RequestMapping(value = "/fin",method = RequestMethod.POST)
    String test(Integer uid);

    @RequestMapping(value = "/t2",method = RequestMethod.POST)
    String test2();

    @RequestMapping(value = "/t3",method = RequestMethod.POST)
    User test3();
}
