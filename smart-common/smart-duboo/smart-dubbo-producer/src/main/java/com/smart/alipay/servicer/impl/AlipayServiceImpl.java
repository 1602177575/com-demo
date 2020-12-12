package com.smart.alipay.servicer.impl;

import com.smart.pay.service.AliPayService;
import org.apache.dubbo.config.annotation.Service;

@Service
public class AlipayServiceImpl implements AliPayService {
    @Override
    public String webPay() {
        return "web 成功支付";
    }

    @Override
    public String appPay() {
        return "app 成功支付";
    }
}
