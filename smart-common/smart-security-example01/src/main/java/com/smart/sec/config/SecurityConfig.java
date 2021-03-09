package com.smart.sec.config;


import com.smart.sec.handler.LoginErroHandler;
import com.smart.sec.handler.LoginSuccessHandler;
import com.smart.sec.handler.NoPermissionHandler;
import com.smart.sec.handler.OnLogoutSuccessHandler;
import com.smart.sec.sevice.impl.CustomerUserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


@EnableWebSecurity
@Component
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    LoginErroHandler loginErroHandler;
    @Resource
    LoginSuccessHandler loginSuccessHandler;
    @Resource
    NoPermissionHandler noPermissionHandler;
    @Resource
    UserDetailsService userDetailsService;
    @Resource
    OnLogoutSuccessHandler onLogoutSuccessHandler;


    /**
     * Http
     * 登陆 登出  授权过滤器
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //首页所有人可以访问 功能页只有对应权限的人才能访问

        http.authorizeRequests()
                .antMatchers("/").permitAll()//首页可以访问
                //超级管理员权限

                //需要特定权限
                //.antMatchers("/level/**").hasRole("VIP")
                //只要有 就行
//                .antMatchers("/user/**").hasAnyAuthority("USER","VIP","ADMIN")
                //任何人都能访问这个请求 验证码的
                .antMatchers("/captcha").permitAll()
                //登陆即可访问
//                .antMatchers("/list").permitAll()
                //所有的请求
                .anyRequest()
                .authenticated();

        //每页权限默认跳转到登陆页
        http.formLogin()
                //定义登陆界面，未登录的时候，慧访问这个接口
                .loginPage("/login.html")
                //处理登陆的接口 不用写框架自带
                .loginProcessingUrl("/login")
                //放开登陆接口
                .permitAll()
                //登陆成功的处理器
                .successHandler(loginSuccessHandler)
                //登陆失败的处理器
                 .failureHandler(loginErroHandler)
                //和表单登陆相关的统统直接通过
                 .permitAll();

        http.exceptionHandling()
                //无权访问处理
                .accessDeniedHandler(noPermissionHandler);

        //开启 注销（登出）功能
        http.logout()
                .logoutUrl("/logout")
                //如果需要登出后的操作可以配置
                .logoutSuccessHandler(onLogoutSuccessHandler)
                //退出后清空cookie
                .deleteCookies("authenticated","JSESSIONID","remember-me")
                .permitAll()
                .and()
                .httpBasic()
                .and()
                .csrf()
                .disable();

        //记住我功能
        http.authorizeRequests()
                .and()
                .rememberMe();

        // 关闭跨域请求
        http.cors().disable();
        // 关闭跨域攻击
        http.csrf().disable();
        // 5. session的配置  如果需要只创建一个
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
        //6. 禁用缓存
        http.headers().cacheControl().disable();

    }

    /**
     * 认证
     * 可以从内存 也可以从数据库
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //配置认证 密码一定要加密 new BCryptPasswordEncoder().encode("密码")
        auth.inMemoryAuthentication()
                .passwordEncoder(passwordEncoder())
                .withUser("123")
                .password(passwordEncoder().encode("123"))
                .roles("USER");

                //后续可以无限加 也可以从数据库中加载
                //配置认证方法
                auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        //放行静态资源
        web.ignoring()
                .antMatchers(HttpMethod.GET,
                        "/swagger-resources/**",
                        "/PearAdmin/**",
                        "/component/**",
                        "/admin/**",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js",
                        "/swagger-ui.html",
                        "/webjars/**",
                        "/v2/**",
                        "/druid/**","/test/**");
    }

    /**
     * 密码加密方法
     * @return
     */
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    /**
     * 验证对象实现类
     * @return
     */
    @Bean("UserDetailsService")
   public UserDetailsService userDetailsService(){
        return new CustomerUserDetailsServiceImpl();
   }
}
