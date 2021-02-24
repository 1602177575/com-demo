package com.smart.sec.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.smart.sec.pojo.Role;
import org.apache.ibatis.annotations.Param;

public interface RoleMapper extends BaseMapper<Role> {

    //根据用户名获得 用户的角色权限级别
    String findRoleByUserName(@Param("username") String username);

}