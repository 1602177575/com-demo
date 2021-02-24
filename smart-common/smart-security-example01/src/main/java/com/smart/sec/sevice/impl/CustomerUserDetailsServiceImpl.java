package com.smart.sec.sevice.impl;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.smart.sec.dto.RoleDto;
import com.smart.sec.dto.UserDetailDto;
import com.smart.sec.mapper.MenuMapper;
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String password = userService.findUserByName(username).getPassword();
        //    声明用户授权
        List<Menu> roleUser = createRoleUser(username);
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roleUser.size());
        roleUser.forEach(Menu -> {
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(Menu.getAuthority());
        grantedAuthorities.add(grantedAuthority);
    });
    // 由框架完成认证工作
        return new org.springframework.security.core.userdetails.User(
                username,
                password,
                grantedAuthorities);
    }

    //获取用户权限
    public List<Menu> createRoleUser(String username){
        List<Menu> roles = menuMapper.findRolesByUserName(username);
        //搞出来打印看看权限
        Set<String> list = new HashSet<>();
        roles.forEach(r->{
            list.add(r.getPermission());
        });
        log.info("该用户的权限有"+list);
        return roles;
    }


}
