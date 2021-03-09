package com.smart.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.smart.auth.entity.UserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface UserRoleMapper extends BaseMapper<UserRole> {

    Set<String> findUserPermission(@Param("username") String username);
}