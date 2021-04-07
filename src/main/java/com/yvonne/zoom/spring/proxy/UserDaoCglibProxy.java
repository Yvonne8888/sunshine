package com.yvonne.zoom.spring.proxy;

import com.yvonne.zoom.spring.dao.impl.UserDaoImpl;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 利用CGLIB的动态代理技术( 第三方代理，己经被Spring集成)继承实现
 *
 * @author ceshi
 * @date 2021/4/721:52
 */
public class UserDaoCglibProxy implements MethodInterceptor {
    //引入目标对象

    //1.产生一个代理对象
    public UserDaoImpl getProxyObject(){
        //1.1) 得到Enhancer对象
        Enhancer enhancer = new Enhancer();
        //1.2) 设置Enhancer对象的父对象
        enhancer.setSuperclass(UserDaoImpl.class);
        //1.3) 设置enhancer对象要做的事情
        enhancer.setCallback(this);
        //1.4) 创建代理对象
        UserDaoImpl userDao = (UserDaoImpl) enhancer.create();
        //1.5) 返回代理对象
        return userDao;
    }

    @Override
    public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        //1.取得方法名
        if(method.getName().equals("query")){
            return  methodProxy.invokeSuper(proxy, args);
        }
        checkSecurity();		//检查安全性
        return methodProxy.invokeSuper(proxy, args);
    }
    //检查安全性
    private void checkSecurity(){
        System.out.println("检查安全性.");
    }
}
