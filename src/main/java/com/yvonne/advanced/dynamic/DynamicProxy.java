package com.yvonne.advanced.dynamic;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理：
 *  代理一个接口下的多个实现类，通过接口中的方法名，在动态生成的代理中调用业务实现类的同名方法，需要接口声明来创建代理实例
 * 静态代理：
 *  代理一个类，事先已经知道代理的是什么
 * AOP编程是基于动态代理实现的，比如Spring，Hibernate等框架
 *
 * @author Yvonne Wang
 */
public class DynamicProxy {
    public static void main(String[] args) {
        try {
            InterfaceDemo interfaceDemo = (InterfaceDemo) new JdkProxyObject().bind(new InterfaceDemoImpl());
            interfaceDemo.sayHello();
        }catch (Exception e){
            System.out.println("JDK动态代理用接口来声明代理对象时的异常：" + e.getMessage());
        }

        try {
            InterfaceDemoImpl interfaceDemoImpl = (InterfaceDemoImpl) new JdkProxyObject().bind(new InterfaceDemoImpl());
            interfaceDemoImpl.sayHello();
        }catch (Exception e){
            System.out.println("JDK动态代理用接口实现类来声明代理对象时的异常：" + e.getMessage());
        }

        System.out.println("----------------");






    }

}

/**
 * JDK动态代理
 *  InvocationHandler接口的invoke方法，但注意的是代理的是接口，也就是你的业务类必须要实现接口，通过Proxy里的newProxyInstance得到代理对象
 */
class JdkProxyObject implements InvocationHandler {
    private Object target;
    public Object bind(Object target) {
        this.target = target;
        //返回代理后的对象
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("JDK 动态代理操作！");
        //执行被代理的对象，实际需要被执行的方法
        return method.invoke(target,args);
    }
}

/**
 * CGLIB动态代理
 *  代理的是类，不需要业务类继承接口，通过派生的子类来实现代理
 *  通过在运行时，动态修改字节码达到修改类的目的
 */
//class CglibProxyObject implements MethodInterceptor {
//
//}


interface InterfaceDemo{
    /**
     * 接口方法
     */
    void sayHello();
}
class InterfaceDemoImpl implements InterfaceDemo{
    @Override
    public void sayHello() {
        System.out.println("say hello...");
    }
}

