package com.smart.auth.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
    * 角色权限表
    */
@Data
@TableName(value = "tb_role_permission")
public class RolePermission {
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    /**
     * 角色 ID
     */
    @TableField(value = "role_id")
    private Long roleId;

    /**
     * 权限 ID
     */
    @TableField(value = "permission_id")
    private Long permissionId;

    public static final String COL_ID = "id";

    public static final String COL_ROLE_ID = "role_id";

    public static final String COL_PERMISSION_ID = "permission_id";
}