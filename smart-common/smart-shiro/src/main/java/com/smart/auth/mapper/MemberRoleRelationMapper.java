package com.smart.auth.mapper;


import com.smart.auth.dto.RoleDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
public interface MemberRoleRelationMapper {
  List<RoleDto> selectRoleById(@Param("id") int memberid);
}