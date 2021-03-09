package com.smart.order.service;

import com.smart.order.dto.OrderDto;

public interface OrderService {
    String createOrder(OrderDto orderDto);
}
