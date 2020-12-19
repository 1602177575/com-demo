package com.smart.security.service.impl;

import com.smart.security.dto.UserDto;
import com.smart.security.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.annotation.Resource;

/**
 * @author mtl
 */
@Slf4j
public class CustomerUserDetailsServiceImpl implements UserDetailsService {

    @Resource
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDto userDto = userService.selectUserByName(username);
        if(userDto == null){
            log.info("账号或者密码错误");
        }
        return userDto;
    }
}
