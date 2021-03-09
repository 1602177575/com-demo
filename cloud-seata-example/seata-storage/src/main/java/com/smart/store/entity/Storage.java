package com.smart.store.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "storage")
public class Storage {
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    /**
     * 产品id
     */
    @TableField(value = "product_id")
    private Long productId;

    /**
     * 总库存
     */
    @TableField(value = "total")
    private Integer total;

    /**
     * 已用库存
     */
    @TableField(value = "used")
    private Integer used;

    /**
     * 剩余库存
     */
    @TableField(value = "residue")
    private Integer residue;

    public static final String COL_ID = "id";

    public static final String COL_PRODUCT_ID = "product_id";

    public static final String COL_TOTAL = "total";

    public static final String COL_USED = "used";

    public static final String COL_RESIDUE = "residue";
}