package com.smart.auth.dto;

import com.smart.auth.entity.Permission;
import lombok.Data;

import java.util.List;

/**
 * 权限组
 */
@Data
public class PermissionDto extends Permission {
    private List<RoleDto> roles;
}
