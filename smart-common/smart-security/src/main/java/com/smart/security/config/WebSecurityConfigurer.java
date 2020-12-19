package com.smart.security.config;


import com.smart.security.handler.LoginErroHandler;
import com.smart.security.handler.LoginSuccessHandler;
import com.smart.security.service.impl.CustomerUserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;


/**
 * @author mtl
 */
@Configuration
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {
    @Resource
    UserDetailsService userDetailsService;
    @Resource
    LoginErroHandler loginErroHandler;
    @Resource
    LoginSuccessHandler loginSuccessHandler;
    @Resource
    PasswordEncoder passwordEncoder;

    /**
     * 安全过滤配置的方法
     * 认证
     * 授权
     * 过滤
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {


        /**
         * 采用表单进行登陆 配置
         */
        http.formLogin()
                //请求认证
            .loginPage("/login")
                //登陆成功处理的回调接口
            .successHandler(loginSuccessHandler)
                //登陆失败时回调的接口
            .failureHandler(loginErroHandler)
                //登陆成功后返回指定的界面
                .defaultSuccessUrl("/index")
                //放开登陆
            .permitAll();

        /**
         * 2. 登出操作配置信息
         */
        http.logout()
                .logoutUrl("/logout")
                .permitAll();
        /**
         * 3. 授权认证配置信息
         */
        http.authorizeRequests()
                .antMatchers("/admin").hasAnyRole("ADMIN","DBA")
                //所有的请求
                .anyRequest()
                //标识所有的请求必须授权认证
                .authenticated();
                //记住我的功能


        /**
         *   4. 可选配置
         */
        // 关闭跨域请求
        http.cors().disable();
        // 关闭跨域攻击
        http.csrf().disable();
        // 5. session的配置
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        //6. 禁用缓存
        http.headers().cacheControl().disable();
    }

    /**
     * 从数据去数据认证
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN","DBA");
        //从数据认证
        auth.userDetailsService(userDetailsService)
                //注册密码加密
                .passwordEncoder(passwordEncoder);
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        return new CustomerUserDetailsServiceImpl();
    }

    /**
     * 核心过滤配置方法 (少用）
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }



}
