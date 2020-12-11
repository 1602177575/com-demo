package com.smart.auth.config;


import com.smart.auth.shiro.LoginRealms;
import com.smart.auth.shiro.ShiroRedisSessionManager;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.util.ThreadContext;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.annotation.Resource;

/**
 *  初始化 WebSecurityManager
 *  LoginRealms
 *
 */
@Configuration
public class ShiroConfig {

    @Resource
    RedisCacheManager redisCacheManager;
    @Resource
    RedisSessionDAO redisSessionDAO;
    /**
     * 导入自己实现的配置 自定义Realm 对象
     * @return
     */
    @Bean
    @Primary
    public Realm realm(){
        LoginRealms realms = new LoginRealms();
        //在自定义realm 中添加加密方法
        realms.setCredentialsMatcher(hashedCredentialsMatcher());
        return realms;
    }

    @Bean
    public DefaultWebSecurityManager webSecurityManager(SessionManager sessionManager, Realm realm){
        DefaultWebSecurityManager manager=new DefaultWebSecurityManager();
        manager.setCacheManager(null);
//   注意     版本兼容
        ThreadContext.bind(manager);
        //注册之定义realm
        manager.setRealm(realm);
        //注册session 对象
        manager.setSessionManager(sessionManager);
        //设置redis 缓存
        manager.setCacheManager(redisCacheManager);

        return manager;
    }

    /**
     * 1> 那些是要放行  步需要认证
     * 2> 那些需要认证才能访问
     *  登陆拦截器
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

    /**
     * 配置session
     * @return
     */
    @Bean
    public ShiroRedisSessionManager sessionManager(){
        ShiroRedisSessionManager sessionManager=new ShiroRedisSessionManager();
        //开启是否删除无用session 信息
        sessionManager.setDeleteInvalidSessions(true);
        //开启定时检测过去信息事件
        sessionManager.setSessionValidationSchedulerEnabled(true);
        sessionManager.setSessionValidationInterval(60*60*1000);
        //取消url 后面的jessionId
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        //设置session 全局过期时间
        sessionManager.setGlobalSessionTimeout(7*24*60*60);
        sessionManager.setSessionDAO(redisSessionDAO);
        return  sessionManager;
    }

    /**
     * 解决AOP 的兼容问题
     *
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator creator=new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }

    /**
     * 内置密码的加密
     * 认证加密
     * 注册后加密
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher matcher=new HashedCredentialsMatcher();
        //SHA-256 加密算法
        matcher.setHashAlgorithmName(Sha256Hash.ALGORITHM_NAME);
        //加密的次数
        matcher.setHashIterations(100);
        //转换为16进制的数据
        matcher.setStoredCredentialsHexEncoded(true);
        return matcher;
    }

}
