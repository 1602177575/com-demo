package com.smart.auth.shiro;


import com.smart.auth.dto.MemberDto;
import com.smart.auth.dto.RoleDto;
import com.smart.auth.mapper.MemberRoleRelationMapper;
import com.smart.auth.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 继承 AuthorizingRealm
 * 来完成登录和权限的验证
 */



public class LoginRealms extends AuthorizingRealm {
    @Resource
    MemberRoleRelationMapper memberRoleRelationMapper;
    @Resource
    UserService userService;
    /**
     *  * 授权
     *  * 获取权限
     *  主要是用于当前登录用户进行授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取当前登录的角色信息
        MemberDto memberDto = (MemberDto) principalCollection.getPrimaryPrincipal();
        //从数据库中查询角色的权限
        List<RoleDto> roleDtoList = memberRoleRelationMapper.selectRoleById(memberDto.getMid());
        Set<String> roleSet= new HashSet<>();
        Set<String> permissionSet = new HashSet<>();
        //获取用户的权限组
        roleDtoList.forEach(role->{
            roleSet.add(role.getRoleName());
            role.getPermissionDtoList().forEach(permissionDto -> {
                permissionSet.add(permissionDto.getPerName());
            });
        });
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        info.setRoles(roleSet); //角色名称
        info.setStringPermissions(permissionSet); //权限地址 例/admin 等权限
        return info;
    }

    /**
     * 主要是对用户进行验证
     * 调用currUser.login(token)方法时会调用doGetAuthenticationInfo方法。
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        //获取 authenticationToken
        String username = (String) authenticationToken.getPrincipal();
        //通过用户名获取到用户从数据库中查询到的权限信息
        MemberDto memberDto = userService.selectByName(username);
        //进行逻辑判断 权限判断
        if(StringUtils.isEmpty(memberDto)){
            throw new UnknownAccountException("账号不存在");
        }
        if(memberDto.getLocked()){
            throw new LockedAccountException("账号被锁定");
        }
        /**
         * 创建 AuthenticationInfo 返回
         *
         *
         * Object principal, 从数据库查询到的对象 也可以是名字（不推荐）
         * Object hashedCredentials,  从数据库中查询到的用户密码
         * ByteSource credentialsSalt,  md5加密的盐 可选
         * String realmName  从token 中获取的用户名
         *
         */

        return new SimpleAuthenticationInfo(memberDto,memberDto.getPassword(),null,username);
    }




}
