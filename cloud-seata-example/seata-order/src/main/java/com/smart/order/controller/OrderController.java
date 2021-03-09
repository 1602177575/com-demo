package com.smart.order.controller;

import com.smart.order.dto.OrderDto;
import com.smart.order.service.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Resource
    OrderService orderService;

    @PostMapping("/")
    public String create() {
        OrderDto orderDto = new OrderDto();
        orderDto.setUserId(1);
        orderDto.setProductId(1);
        orderDto.setQuantity(1);
        return orderService.createOrder(orderDto);
    }
}
