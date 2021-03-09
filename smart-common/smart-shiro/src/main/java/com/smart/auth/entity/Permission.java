package com.smart.auth.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
    * 权限表
    */
@Data
@TableName(value = "tb_permission")
public class Permission {
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    /**
     * 父权限
     */
    @TableField(value = "parent_id")
    private Long parentId;

    /**
     * 权限名称
     */
    @TableField(value = "`name`")
    private String name;

    /**
     * 权限英文名称
     */
    @TableField(value = "enname")
    private String enname;

    /**
     * 授权路径
     */
    @TableField(value = "url")
    private String url;

    /**
     * 备注
     */
    @TableField(value = "description")
    private String description;

    @TableField(value = "created")
    private Date created;

    @TableField(value = "updated")
    private Date updated;

    public static final String COL_ID = "id";

    public static final String COL_PARENT_ID = "parent_id";

    public static final String COL_NAME = "name";

    public static final String COL_ENNAME = "enname";

    public static final String COL_URL = "url";

    public static final String COL_DESCRIPTION = "description";

    public static final String COL_CREATED = "created";

    public static final String COL_UPDATED = "updated";
}