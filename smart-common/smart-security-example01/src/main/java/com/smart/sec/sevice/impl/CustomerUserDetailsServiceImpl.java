package com.smart.sec.sevice.impl;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.smart.sec.dto.RoleDto;
import com.smart.sec.dto.UserDetailDto;
import com.smart.sec.mapper.MenuMapper;
import com.smart.sec.mapper.RoleMapper;
import com.smart.sec.mapper.UserMapper;
import com.smart.sec.pojo.Menu;
import com.smart.sec.pojo.User;
import com.smart.sec.sevice.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.security.Permission;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * 实现 UserDetailsService 方法 框架的
 */

@Slf4j
@Service
public class CustomerUserDetailsServiceImpl implements UserDetailsService {

    @Resource
    MenuMapper menuMapper;
    @Resource
    UserService userService;
    @Resource
    RoleMapper roleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        String password = userService.findUserByName(username).getPassword();

        //根据用户名 获取角色名
        String roleName = roleMapper.findRoleByUserName(username);
        List<String> menuList = selectRole(roleName);
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        menuList.forEach(menu -> {
        grantedAuthorities.add(new SimpleGrantedAuthority(menu));
    });
        log.info("当前用户权限"+grantedAuthorities);

    // 由框架完成认证工作
        return new org.springframework.security.core.userdetails.User(
                username,
                password,
                grantedAuthorities);
    }

    //获取用户权限
    public List<String> selectRole(String rolesName){
        return menuMapper.findRoleByRoleName(rolesName);
    }


}
