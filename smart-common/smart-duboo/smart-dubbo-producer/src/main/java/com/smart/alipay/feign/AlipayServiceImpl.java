package com.smart.alipay.feign;

import com.smart.pay.pojo.User;
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

    @Override
    public User test() {
        User user = new User();
        user.setName("123");
        user.setPassword("123");
        user.setSex(1);
        return user;
    }
}
