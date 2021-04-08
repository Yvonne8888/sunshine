package com.yvonne.zoom.spring.test;

import com.yvonne.zoom.spring.domain.Person;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Yvonne Wang
 * @date 2021/04/08 11:48
 **/
public class SpringBeanTest {
    public static void main(String[] args) {
        System.out.println("------Bean的生命周期------");
        System.out.println("开始初始化容器");
        ApplicationContext factory = new ClassPathXmlApplicationContext("beans1.xml");
        System.out.println("初始化容器成功");

        //得到Person，并使用
        Person person = factory.getBean("person", Person.class);
        System.out.println(person);

        System.out.println("现在开始关闭容器");
        //关闭容器使用的是实际是AbstractApplicationContext的子方法。
        ((ClassPathXmlApplicationContext)factory).registerShutdownHook();


    }
}
