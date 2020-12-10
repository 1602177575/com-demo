package com.smart.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.smart.auth.dto.MemberDto;
import com.smart.auth.entity.Member;
import com.smart.auth.mapper.MemberMapper;
import com.smart.auth.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    MemberMapper memberMapper;
    @Override
    public MemberDto selectByName(String name) {
        QueryWrapper qw=new QueryWrapper();
        qw.eq(Member.COL_USERNAME,name);
        Member member = memberMapper.selectOne(qw);
        MemberDto dto = new MemberDto();
        BeanUtils.copyProperties(member,dto);
        return dto;
    }
}
