package com.smart.member.service.impl;

import com.smart.db.service.DbService;
import com.smart.member.service.MemberService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MemberServiceImpl implements MemberService {

    @Resource
    DbService dbService;

    @Override
    public String getVerifyCode(String phone) {
        return null;
    }

    @Override
    public String login(String phone, String code) {
        return null;
    }
}
