package com.smart.auth.config;


import com.smart.auth.shiro.LoginRealms;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.util.ThreadContext;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 *  初始化 WebSecurityManager
 *  LoginRealms
 *
 */
@Configuration
public class ShiroConfig {

    /**
     * 导入自己实现的配置
     * @return
     */
    @Bean
    @Primary
    public Realm realm(){
        return new LoginRealms();
    }

    @Bean
    public DefaultWebSecurityManager webSecurityManager(Realm realm){
        DefaultWebSecurityManager manager=new DefaultWebSecurityManager();
        manager.setCacheManager(null);
//   注意     版本兼容
        ThreadContext.bind(manager);
        manager.setRealm(realm);
        return manager;
    }

    /**
     * 1> 那些是要放行  步需要认证
     * 2> 那些需要认证才能访问
     *
     * @return
     */
    @Bean("shiroFilter")
    public ShiroFilterChainDefinition filterChainDefinition() {
        DefaultShiroFilterChainDefinition definition = new DefaultShiroFilterChainDefinition();
        //需要放行的的路径
        definition.addPathDefinition("/login", "anon");
        definition.addPathDefinition("/register", "anon");
        definition.addPathDefinition("/druid/**", "anon");
        // 配置用户登出
        definition.addPathDefinition("/logout", "logout");
        // admin 必须通过认证才能访问
        definition.addPathDefinition("/admin/**", "authc");
        definition.addPathDefinition("/**", "authc");
        return definition;
    }


}
