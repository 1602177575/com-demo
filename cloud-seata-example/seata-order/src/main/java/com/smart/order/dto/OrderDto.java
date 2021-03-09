package com.smart.order.dto;

import lombok.Data;

@Data
public class OrderDto {
    private long id;
    private long userId;
    private long productId;
    private int quantity;
    private long money;
}
