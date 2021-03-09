package com.smart.order.service.impl;

import com.smart.order.dto.OrderDto;
import com.smart.order.entity.Order;
import com.smart.order.feign.StoreService;
import com.smart.order.mapper.OrderMapper;
import com.smart.order.service.OrderService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * undo-log 表
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    StoreService storeService;
    @Resource
    OrderMapper orderMapper;

    @Override
    @GlobalTransactional
    public String createOrder(OrderDto orderDto) {
        Order order = new Order();
        BeanUtils.copyProperties(orderDto, order);
        int insert = orderMapper.insert(order);
        storeService.dec((int) orderDto.getProductId(), orderDto.getQuantity());
        return insert + "";
    }
}
