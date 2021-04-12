package com.yvonne.zoom.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yvonne Wang
 * @date 2021/4/1221:40
 */
public class CustomPermissionsRealm extends AuthorizingRealm {

    /**
     * 授权方法(调用授权方法一定要通过认证方法)
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String usercode = (String) principalCollection.getPrimaryPrincipal();
        if (usercode == null) {
            return null;
        }
        List<String> permissions = new ArrayList<>();
        permissions.add("emp:create");
        permissions.add("emp:update");
        permissions.add("emp:delete");
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addStringPermissions(permissions);

        return simpleAuthorizationInfo;
    }

    /**
     * 认证方法
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //用户名
        String usercode = (String) authenticationToken.getPrincipal();
        if (usercode == null) {
            return null;
        }
        //用户名去数据库查询密码
        String password = "123";
        return new SimpleAuthenticationInfo(usercode, password, this.getName());
    }
}
