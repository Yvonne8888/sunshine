package com.yvonne.zoom.spring.test;

import com.yvonne.zoom.spring.domain.CollectionBean;
import com.yvonne.zoom.spring.domain.Emp;
import org.junit.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * @author Yvonne Wang
 * @date 2021/4/522:44
 * Spring DI依赖注入
 */
public class SpringDiTest {

    ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");

    /**
     * set方法注入
     */
    @Test
    public void test04(){
        Emp emp04 = (Emp) ac.getBean("emp04");
        System.out.println("emp04：" + emp04);
        ac.close();
    }

    /**
     * 构造方法注入
     */
    @Test
    public void test05(){
        //通过name
        Emp emp05 = (Emp) ac.getBean("emp05");
        System.out.println("emp05：" + emp05);
        //通过index
        Emp emp06 = (Emp) ac.getBean("emp06");
        System.out.println("emp06：" + emp06);
        ac.close();
    }

    /**
     * p名称空间注入
     */
    @Test
    public void test07(){
        Emp emp07 = (Emp) ac.getBean("emp07");
        System.out.println("emp07：" + emp07);
        ac.close();
    }

    /**
     * spel名称空间注入
     */
    @Test
    public void test08(){
        Emp emp08 = (Emp) ac.getBean("emp08");
        System.out.println("emp08：" + emp08);
        ac.close();
    }

    /**
     * 数组注入
     */
    @Test
    public void testCb01(){
        CollectionBean cb01 = (CollectionBean) ac.getBean("cb01");
        for (String str : cb01.getStrArr()) {
            System.out.println(str);
        }
        ac.close();
    }

    /**
     * List集合注入
     */
    @Test
    public void testCb02(){
        CollectionBean cb02 = (CollectionBean) ac.getBean("cb02");
        for (Emp str : cb02.getList()) {
            System.out.println(str);
        }
        ac.close();
    }

    /**
     * Map集合注入
     */
    @Test
    public void testCb03(){
        CollectionBean cb03 = (CollectionBean) ac.getBean("cb03");
        Map<Integer, Emp> map = cb03.getMap();
        Set<Integer> entrySet = map.keySet();
        for (Integer s:entrySet) {
            System.out.println(map.get(s));
        }
        ac.close();
    }

    /**
     * Properties集合注入
     */
    @Test
    public void testCb04(){
        CollectionBean cb04 = (CollectionBean) ac.getBean("cb04");
        Properties properties = cb04.getProperties();
        System.out.println("jdbcDriver:" + properties.get("jdbcDriver"));
        System.out.println("jdbcUrl:" + properties.get("jdbcUrl"));
        System.out.println("username:" + properties.get("username"));
        System.out.println("password:" + properties.get("password"));
        ac.close();
    }

    /**
     * 对象工厂方法创建对象+spel注入
     */
    @Test
    public void test09(){
        Emp emp09 = (Emp) ac.getBean("emp09");
        Emp emp10 = (Emp) ac.getBean("emp10");
        //Emp{name='yvonne', age=0}
        System.out.println(emp09);
        System.out.println(emp10);

        //DefaultListableBeanFactory不能解析spel，会把spel直接当做字符串
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(new ClassPathResource("beans.xml"));
        Emp emp = (Emp) factory.getBean("emp09");
        Emp emp1 = (Emp) factory.getBean("emp10");
        //Emp{name='#{emp05.name}', age=0}
        System.out.println(emp);
        System.out.println(emp1);
        ac.close();
    }

}
