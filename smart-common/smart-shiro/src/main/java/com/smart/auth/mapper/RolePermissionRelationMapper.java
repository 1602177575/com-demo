package com.smart.auth.mapper;


import com.smart.auth.dto.PermissionDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RolePermissionRelationMapper  {
    List<PermissionDto> permissionList(@Param("roleId")int roleId);
}