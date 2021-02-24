package com.smart.sec.sevice.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.smart.sec.mapper.UserMapper;
import com.smart.sec.pojo.User;
import com.smart.sec.sevice.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;

    @Override
    public User findUserByName(String name) {
        return userMapper.selectOne(new QueryWrapper<User>().eq(User.COL_USER_NAME, name));
    }




}

