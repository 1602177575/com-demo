package com.smart.auth.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Shiro 配置类
 * 先配置realm 对象 再去接管这个对象 再链接到前端
 *
 *
 */
@Configuration
public class ShiroConfig {

    //shiroFilterFactoryBean 设置安全管理器
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        //设置安全管理器
        bean.setSecurityManager(defaultWebSecurityManager);
        //添加shiro 内置过滤器
        /**
         * anon : 无需认证就可以访问
         * authc: 必须认证了才能访问
         * user: 必须有 记住我 功能才能用
         * perms  拥有某个资源的权限才能访问
         * role 拥有某个角色权限才能访问
         * /admin/**=anon ：无参，表示可匿名访问
         * /admin/user/**=authc ：无参，表示需要认证才能访问
         * /admin/user/**=authcBasic ：无参，表示需要httpBasic认证才能访问
         * /admin/user/**=ssl ：无参，表示需要安全的URL请求，协议为https
         * /home=user ：表示用户不一定需要通过认证，只要曾被 Shiro 记住过登录状态就可以正常发起 /home 请求
         * /edit=authc,perms[admin:edit]：表示用户必需已通过认证，并拥有 admin:edit 权限才可以正常发起 /edit 请求
         * /admin=authc,roles[admin] ：表示用户必需已通过认证，并拥有 admin 角色才可以正常发起 /admin 请求
         * /admin/user/**=port[8081] ：当请求的URL端口不是8081时，跳转到schemal://serverName:8081?queryString
         * /admin/user/**=rest[user] ：根据请求方式来识别，相当于 /admins/user/**=perms[user:get]或perms[user:post] 等等
         * /admin**=roles["admin,guest"] ：允许多个参数（逗号分隔），此时要全部通过才算通过，相当于hasAllRoles()
         * /admin**=perms["user:add:*,user:del:*"]：允许多个参数（逗号分隔），此时要全部通过才算通过，相当于isPermitedAll()
         *
         */
        //配置过滤器 加入
        Map<String, String> filterMap = new LinkedHashMap<>();
        //登陆即可访问
        //放行
        filterMap.put("/index/*","anon");
        //授权
        filterMap.put("/user/add","perms[user:add]");
        filterMap.put("/user/update","perms[user:update]");
        filterMap.put("/logout","logout");
        bean.setFilterChainDefinitionMap(filterMap);
        //未授权界面
        bean.setUnauthorizedUrl("/Ex/401");
        //登陆界面 如果无权直接跳转
        bean.setLoginUrl("/tologin");
        return bean;
    }

    //dafaultWebSecurityManager 关联UserRealm
    @Bean(name = "defaultWebSecurityManager")
    public DefaultWebSecurityManager defaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager SecurityManager = new DefaultWebSecurityManager();
        //关联UserRealm
        SecurityManager.setRealm(userRealm);
        return SecurityManager;
    }

    //创建realm 对象 自定义 直接外接
    @Bean(name = "userRealm")
    public UserRealm userRealm(){
        return new UserRealm();
    }


}
