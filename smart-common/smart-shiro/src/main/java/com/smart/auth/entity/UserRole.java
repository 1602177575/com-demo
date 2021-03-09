package com.smart.auth.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
    * 用户角色表
    */
@Data
@TableName(value = "tb_user_role")
public class UserRole {
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    /**
     * 用户 ID
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 角色 ID
     */
    @TableField(value = "role_id")
    private Long roleId;

    public static final String COL_ID = "id";

    public static final String COL_USER_ID = "user_id";

    public static final String COL_ROLE_ID = "role_id";
}