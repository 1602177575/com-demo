package com.smart.db.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
    * 订单表
    */
@Data
@TableName(value = "tb_order")
public class Order {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField(value = "user_id")
    private Long userId;

    @TableField(value = "product_id")
    private Long productId;

    @TableField(value = "quantity")
    private Integer quantity;

    @TableField(value = "money")
    private Long money;

    @TableField(value = "status")
    private Integer status;

    public static final String COL_ID = "id";

    public static final String COL_USER_ID = "user_id";

    public static final String COL_PRODUCT_ID = "product_id";

    public static final String COL_QUANTITY = "quantity";

    public static final String COL_MONEY = "money";

    public static final String COL_STATUS = "status";
}