package com.smart.auth.Service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.smart.auth.Service.UserService;
import com.smart.auth.entity.User;
import com.smart.auth.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;

    @Override
    public User findUserByName(String name) {
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.eq(User.COL_USERNAME,name);
        return userMapper.selectOne(qw);
    }
}
