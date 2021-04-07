package com.yvonne.advanced.dynamic;


import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 反射
 *
 * @author Yvonne Wang
 */
public class Reflect {
    public void reflectMethod() throws Exception {
        //Java的反射首先是要获得Java中的反射类的字节码，三种方式获取字节码。
        // 1）Class.format(className)
        // 2）类名.class
        // 3）this.getClass()
        //将字节码中的方法，变量，构造函数等映射成相应的Method，Filed，Constructor等类
        Class<?> clazz1 = Class.forName("com.yvonne.advanced.dynamic.Reflect.TempletDemo");
        Class<?> clazz2 = TempletDemo.class;
        Class<?> clazz3 = (new TempletDemo()).getClass();

        //获取所有字段（包括私有字段）
        Field[] fields = clazz1.getDeclaredFields();
        for (Field f: fields) {
            //以整数形式返回此Filed对象表示的字段的Java修饰符
            int modifiers = f.getModifiers();
            String fName = f.getName();
        }

        //通过TempletDemo类的字节码clazz1执行这个类的add方法(String str, int ... args)
        //newInstance()静态方法来实例化对象，一定要有无参构造方法
        Object obj1 = clazz1.newInstance();
        Method method1 = clazz1.getMethod("add", String.class, int.class, int.class);
        Object returnValue = method1.invoke(obj1, "hello", 2, 3);

        //获取构造方法public TempletDemo(String str, int ... args)
        Constructor<?> constructor1 = clazz1.getConstructor(String.class, int.class, int.class);
        //获取构造方法public TempletDemo()
        Constructor<?> constructor2 = clazz1.getConstructor();
        //通过构造方法创建对象
        Object obj2 = constructor1.newInstance("hello", 4, 5);
        Object obj3 = constructor2.newInstance();

        //获取私有的add(String str)
        Method method2 = clazz1.getDeclaredMethod("add", String.class);
        //访问私有方法需要将其设置为可访问
        method2.setAccessible(true);
        method2.invoke(obj2);

        //获取注解对象（只能获取自定义注解对象）
        Annotation[] annotations = clazz1.getAnnotations();
        for (Annotation a : annotations) {
            System.out.println(a);
        }
    }

    static class TempletDemo{
        public String hi = "hi";
        private String hello = "hello";
        public TempletDemo() {}
        public TempletDemo(String str, int ... args) {}
        public void add(String str, int ... args) {}
        private void add(String str) {}
    }

}
