package com.smart.sec.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Data
@TableName(value = "my_menu")
public class Menu implements GrantedAuthority {
    /**
     * id值
     */
    @TableId(value = "menu_id", type = IdType.INPUT)
    private Integer menuId;

    /**
     * 父级菜单id
     */
    @TableField(value = "parent_id")
    private Integer parentId;

    /**
     * 菜单名称
     */
    @TableField(value = "menu_name")
    private String menuName;

    /**
     * 菜单图标
     */
    @TableField(value = "icon")
    private String icon;

    /**
     * url
     */
    @TableField(value = "url")
    private String url;

    /**
     * 权限
     */
    @TableField(value = "permission")
    private String permission;

    /**
     * 排序
     */
    @TableField(value = "sort")
    private Integer sort;

    /**
     * 类型
     */
    @TableField(value = "`type`")
    private Boolean type;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    public static final String COL_MENU_ID = "menu_id";

    public static final String COL_PARENT_ID = "parent_id";

    public static final String COL_MENU_NAME = "menu_name";

    public static final String COL_ICON = "icon";

    public static final String COL_URL = "url";

    public static final String COL_PERMISSION = "permission";

    public static final String COL_SORT = "sort";

    public static final String COL_TYPE = "type";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";

    @Override
    public String getAuthority() {
        return this.permission;
    }
}