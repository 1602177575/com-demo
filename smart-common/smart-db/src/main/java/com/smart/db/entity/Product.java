package com.smart.db.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
    * 产品表
    */
@Data
@TableName(value = "product")
public class Product {
    @TableId(value = "product_id", type = IdType.AUTO)
    private Long productId;

    /**
     * 产品名字
     */
    @TableField(value = "product_name")
    private String productName;

    /**
     * 库存
     */
    @TableField(value = "product_value")
    private Long productValue;

    public static final String COL_PRODUCT_ID = "product_id";

    public static final String COL_PRODUCT_NAME = "product_name";

    public static final String COL_PRODUCT_VALUE = "product_value";


}