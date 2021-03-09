package com.smart.auth.config;

import com.smart.auth.Service.UserService;
import com.smart.auth.entity.User;
import com.smart.auth.mapper.UserRoleMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import javax.annotation.Resource;
import java.util.Set;

/**
 * 自定义reaml
 */

@Slf4j
public class UserRealm extends AuthorizingRealm {

    @Resource
    UserService userService;
    @Resource
    UserRoleMapper userRoleMapper;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.info("执行了授权");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //获取当前对象
        User user = (User) principals.getPrimaryPrincipal();
        //再根据用户获取权限字段  例 : user:add
        Set<String> userPermission = userRoleMapper.findUserPermission(user.getUsername());
        info.setStringPermissions(userPermission);
        log.info("该用户拥有的权限"+userPermission.toString());
        return info;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        log.info("执行了认证");
        UsernamePasswordToken usernamePasswordToken= (UsernamePasswordToken) token;
        //获取
        User user = userService.findUserByName(usernamePasswordToken.getUsername());
        if(user==null){
            log.info("账号为空");
            return null;
        }
        return new SimpleAuthenticationInfo(user,user.getPassword(),null,user.getUsername());
    }
}
