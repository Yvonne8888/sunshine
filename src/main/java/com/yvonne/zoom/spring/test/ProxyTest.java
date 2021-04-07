package com.yvonne.zoom.spring.test;

import com.yvonne.zoom.spring.dao.IUserDao;
import com.yvonne.zoom.spring.dao.impl.UserDaoImpl;
import com.yvonne.zoom.spring.proxy.UserDaoCglibProxy;
import com.yvonne.zoom.spring.proxy.UserDaoDynamicProxy;
import com.yvonne.zoom.spring.proxy.UserDaoStaticProxy;
import org.junit.Test;

/**
 * @author Yvonne Wang
 * @date 2021/4/721:43
 */
public class ProxyTest {
    /**
     * 静态代理类测试
     */
    @Test
    public void test01(){
        IUserDao userDao = new UserDaoImpl();
        UserDaoStaticProxy userDaoImplStaticProxy = new UserDaoStaticProxy(userDao);
        userDaoImplStaticProxy.add();
    }

    /**
     * 动态代理类测试
     */
    @Test
    public void test02(){
        IUserDao userDao = new UserDaoImpl();
        UserDaoDynamicProxy udp = new UserDaoDynamicProxy(userDao);
        IUserDao proxyObject = udp.getProxyObject();

        proxyObject.add();
        System.out.println("--------------------------------");
        proxyObject.update();
        System.out.println("--------------------------------");
        proxyObject.delete();
        System.out.println("--------------------------------");
        proxyObject.query();
        System.out.println("--------------------------------");

        //检查代理对象与目标对象的关系：(没有关系，兄弟关系)
        System.out.println("代理类是IUserDao实现类:" + (proxyObject instanceof UserDaoImpl));
    }

    /**
     * 测试CGLIB代理
     */
    @Test
    public void test03(){
        UserDaoCglibProxy ucp = new UserDaoCglibProxy();
        //获取代理对象
        IUserDao proxyObject = ucp.getProxyObject();
        //调用目标对象的方法
        proxyObject.add();
        System.out.println("--------------------------------");
        proxyObject.update();
        System.out.println("--------------------------------");
        proxyObject.delete();
        System.out.println("--------------------------------");
        proxyObject.query();
        System.out.println("--------------------------------");
        proxyObject.add();

        System.out.println("代理类是IUserDao实现类:" + (proxyObject instanceof UserDaoImpl));
    }
}
