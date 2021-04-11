package com.yvonne.zoom.shiro.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

/**
 * @author Yvonne Wang
 * @date 2021/4/1122:27
 */
public class ShiroTest {

    /**
     * 认证方法测试
     */
    @Test
    public void test01(){
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-credentials.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        AuthenticationToken token = new UsernamePasswordToken("yvonne", "123");
        subject.login(token);
        System.out.println("验证是否通过：" + subject.isAuthenticated());
        /*1.如果iniRealm 返回的是null，则抛出：org.apache.shiro.authc.UnknownAccountException
		  2.如果iniRealm 返回的主体对象不为null，则ModularRealmAuthenticator 将此主体对象中的凭证信息（密码）与token 中的密码进行比对，如果一致，则认证通过。否则，由
			ModularRealmAuthenticator 抛出：org.apache.shiro.authc.IncorrectCredentialsException
		*/
    }

    /**
     * 自定义Realm - 认证方法测试
     */
    @Test
    public void test02(){
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-credentials-customRealm.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        AuthenticationToken token = new UsernamePasswordToken("yvonne", "123");
        subject.login(token);
        System.out.println("验证是否通过：" + subject.isAuthenticated());
    }

    /**
     * 自定义Realm散列算法：加盐  - 认证方法测试
     */
    @Test
    public void test03() {
        /**生成hd5hash*/
        String salt = "salt01";
        String oldPassword = "123";
        //加密次数
        int hashInterations = 1;

        //加密算法一：
        Md5Hash md5Hash = new Md5Hash(oldPassword, salt, hashInterations);
        System.out.println(md5Hash.toString());
        //加密算法二：
        SimpleHash simpleHash = new SimpleHash("md5", oldPassword, salt, hashInterations);
        System.out.println(simpleHash.toString());
        System.out.println();
        //加密一次的密码：5634628e805f1c24b77d3acbb167a74f
        //加密两次的密码：d2b93a4fab2a10112371b7336e810616

        /**测试hd5hash*/
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-credentials-customRealmSalt.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        AuthenticationToken token = new UsernamePasswordToken("yvonne", oldPassword);
        subject.login(token);
        System.out.println("验证是否通过：" + subject.isAuthenticated());
    }
}
