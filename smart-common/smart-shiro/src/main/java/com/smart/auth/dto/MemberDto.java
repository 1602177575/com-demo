package com.smart.auth.dto;

import com.smart.auth.entity.Member;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * 用户组
 */

@EqualsAndHashCode(callSuper = true) //生成equals hashcode 方法
@Data
public class MemberDto extends Member implements Serializable {
    private Integer id;
    /**
     * 用户所有权限
     */
    private List<RoleDto> roles;
}
