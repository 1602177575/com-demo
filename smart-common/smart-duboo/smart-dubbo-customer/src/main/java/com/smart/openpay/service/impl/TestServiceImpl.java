package com.smart.openpay.service.impl;

import com.smart.openpay.service.TestService;
import com.smart.pay.service.AliPayService;
import org.apache.dubbo.config.annotation.Reference;

import org.springframework.stereotype.Service;


@Service
public class TestServiceImpl implements TestService {

    @Reference
    AliPayService aliPayService;

    @Override
    public String test() {
        String s = aliPayService.webPay();
        return s+"测试";
    }
}
