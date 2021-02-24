package com.smart.sec.dto;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

/**
 * 权限类型
 */
@Data
public class RoleDto implements GrantedAuthority {

    private String authority;

    @Override
    public String getAuthority() {
        return null;
    }

    RoleDto(String authority){
        this.authority=authority;
    }
}
