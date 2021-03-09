package com.smart.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("storage-service")
public interface StoreService {
    @PutMapping("/decr")
    String dec(@RequestParam("productId") int productId, @RequestParam("quantity") int quantity);
}
