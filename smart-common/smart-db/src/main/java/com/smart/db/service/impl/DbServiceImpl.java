package com.smart.db.service.impl;

import com.smart.db.entity.User;
import com.smart.db.mapper.UserMapper;
import com.smart.db.service.DbService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DbServiceImpl implements DbService {
    @Resource
    UserMapper userMapper;

    @Override
    public User selectUserById(Integer id) {
        User user = userMapper.selectById(1);
        return user;
    }
}
