package com.smart.db.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
    * 优惠券表
    */
@Data
@TableName(value = "coupons")
public class Coupons {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 优惠卷名称
     */
    @TableField(value = "cp_name")
    private String cpName;

    /**
     * 类型
     */
    @TableField(value = "cp_type")
    private String cpType;

    /**
     * 默认手动发放1 自动发放2
     */
    @TableField(value = "cp_auto")
    private Integer cpAuto;

    /**
     * 发放的总量
     */
    @TableField(value = "cp_sum")
    private Long cpSum;

    /**
     * 每个人可以领取的数量 默认1
     */
    @TableField(value = "cp_solo_quantity")
    private Long cpSoloQuantity;

    /**
     * 发放条件 全体 会员 1级会员 2级 默认全体 0 
     */
    @TableField(value = "cp_condition")
    private Long cpCondition;

    /**
     * 发放时间
     */
    @TableField(value = "cp_time")
    private Date cpTime;

    /**
     * 有效范围
     */
    @TableField(value = "cp_scope")
    private Long cpScope;

    public static final String COL_ID = "id";

    public static final String COL_CP_NAME = "cp_name";

    public static final String COL_CP_TYPE = "cp_type";

    public static final String COL_CP_AUTO = "cp_auto";

    public static final String COL_CP_SUM = "cp_sum";

    public static final String COL_CP_SOLO_QUANTITY = "cp_solo_quantity";

    public static final String COL_CP_CONDITION = "cp_condition";

    public static final String COL_CP_TIME = "cp_time";

    public static final String COL_CP_SCOPE = "cp_scope";
}