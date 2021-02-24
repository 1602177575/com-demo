package com.smart.sec.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "my_role_menu")
public class RoleMenu {
    /**
     * 角色id
     */
    @TableId(value = "role_id", type = IdType.INPUT)
    private Integer roleId;

    /**
     * 菜单id
     */
    @TableField(value = "menu_id")
    private Integer menuId;

    public static final String COL_ROLE_ID = "role_id";

    public static final String COL_MENU_ID = "menu_id";
}