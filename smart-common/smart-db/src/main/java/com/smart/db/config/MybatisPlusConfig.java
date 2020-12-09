package com.smart.db.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * 通配符配置扫描xml
 */

@Configuration
@MapperScan("com.smart.**.mapper")
public class MybatisPlusConfig {

    @Bean
    @Primary
    public PaginationInterceptor interceptor(){
        PaginationInterceptor interceptor=new PaginationInterceptor();
        interceptor.setLimit(20L);
        //超过最大 最小页数智能转换
        interceptor.setOverflow(true);
        return interceptor;
    }

}
