package com.smart.sec.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.smart.sec.pojo.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface MenuMapper extends BaseMapper<Menu> {

    List<Menu> findRolesByUserName(@Param("username") String userName);

}