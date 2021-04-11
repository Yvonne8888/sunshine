package com.yvonne.zoom.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

/**
 * @author Yvonne Wang
 * @date 2021/4/1122:46
 */
public class CustomCredentialsMd5SaltRealm extends AuthorizingRealm {

    /**
     * 授权方法
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
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
        //用户名去数据库查询密码（用盐"salt01"通过md5加密一次的密码）
        String hashSaltPassword = "5634628e805f1c24b77d3acbb167a74f";
        String credentialsSalt = "salt01";
        return new SimpleAuthenticationInfo(usercode, hashSaltPassword, ByteSource.Util.bytes(credentialsSalt), this.getName());
    }

    @Override
    public String getName() {
        String name = "xxxx";
        return name;
    }
}
