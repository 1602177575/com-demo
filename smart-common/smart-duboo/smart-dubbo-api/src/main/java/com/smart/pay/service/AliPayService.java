package com.smart.pay.service;

import com.smart.pay.pojo.User;

/**
 * 定义接口方法
 */
public interface AliPayService {

    /**
     * web 支付
     * @return
     */
    String webPay();

    /**
     * 移动支付
     * @return
     */
    String appPay();

    User test();


}
