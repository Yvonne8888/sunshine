package com.yvonne.zoom.spring.proxy;

import com.yvonne.zoom.spring.dao.IUserDao;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 利用JDK的动态代理技术( Proxy.newProxyInstance)实现
 *
 * @author Yvonne Wang
 * @date 2021/4/721:48
 */
public class UserDaoDynamicProxy implements InvocationHandler {
    //引入目标对象
    private IUserDao userDao;
    public UserDaoDynamicProxy(IUserDao userDao) {
        this.userDao = userDao;
    }

    //获取代理对象
    public IUserDao getProxyObject(){
        //参数1：获取当前目标类的类加载器对象
        //参数2：获取目标对象所实现接口的class对象
        //参数3：代表实现了InvocationHandler接口的对象，在此代表当前对象
        IUserDao proxyObject = (IUserDao) Proxy.newProxyInstance(
                userDao.getClass().getClassLoader(),
                userDao.getClass().getInterfaces(),
                this);
        return proxyObject;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return null;
    }
}
