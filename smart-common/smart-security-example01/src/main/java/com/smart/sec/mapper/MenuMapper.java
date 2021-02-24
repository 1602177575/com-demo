package com.smart.sec.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.smart.sec.pojo.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface MenuMapper extends BaseMapper<Menu> {

    //角色名 获取权限菜单详情
    List<Menu> findRolesByUserName(@Param("username") String userName);

    //角色名 获取权限菜单
    List<String> findRoleByRoleName(@Param("roleName") String roleName);

}