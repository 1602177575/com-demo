package com.smart.auth.dto;

import com.smart.auth.entity.Role;
import lombok.Data;

import java.util.List;

@Data
public class RoleDto extends Role {
    private List<PermissionDto> permissionDtoList;
}
