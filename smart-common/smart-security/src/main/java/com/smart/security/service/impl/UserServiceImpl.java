package com.smart.security.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.smart.security.dto.UserDto;
import com.smart.security.entity.User;
import com.smart.security.mapper.UserMapper;
import com.smart.security.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author mtl
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;

    @Override
    public UserDto selectUserByName(String username) {
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.eq(User.COL_USERNAME,username);
        User user = userMapper.selectOne(qw);
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user,userDto);
        return userDto;
    }
}
