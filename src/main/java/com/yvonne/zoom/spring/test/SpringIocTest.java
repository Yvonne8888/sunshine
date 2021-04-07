package com.yvonne.zoom.spring.test;

import com.yvonne.zoom.spring.domain.Emp;
import org.junit.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

/**
 * @author Yvonne Wang
 * @date 2021/4/522:04
 * Spring IOC控制反转
 */
public class SpringIocTest {

    /**如果使用junit 进行测试，需要添加关于日志的两个包:
     com.springsource.org.apache.commons.logging-1.1.1.jar
     com.springsource.org.apache.log4j-1.2.15.jar

     注释代码1：
     //ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
     */

    /**
     * 使用空构造方法创建单例对象
     */
    @Test
    public void test01(){
        //1.得到一个ApplicationContext对象
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        //2.通过上面的对象取得JavaBean对象
        Emp emp01 = (Emp) ac.getBean("emp01");
        Emp emp02 = (Emp) ac.getBean("emp01");
        System.out.println("emp01：" + emp01 );
        //为单例，此时<bean scope="singleton">也即默认值，代表单例模式，所以产生的是同一个对象
        System.out.println("是否单例：" + (emp01==emp02) );
        ac.close();
    }

    /**
     * 使用静态工厂方法创建对象
     */
    @Test
    public void test02(){
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        Emp emp02 = (Emp) ac.getBean("emp02");
        System.out.println("emp02：" + emp02);
        ac.close();
    }

    /**
     * 使用对象工厂方法创建对象
     */
    @Test
    public void test03(){
        //ApplicationContext在Spring容器一启动时，就会将配置文件中所有的bean加载到容器中，所以效率更高。
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        Emp emp03 = (Emp) ac.getBean("emp03");
        System.out.println("是否是Emp对象：" + (emp03 instanceof Emp));
        ac.close();

        System.out.println("----------------");

        //BeanFactory是在需要对象时才会从配置文件中加载对象
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(new ClassPathResource("applicationContext.xml"));
        Emp emp031 = (Emp) factory.getBean("emp03");
        System.out.println("是否是Emp对象：" + (emp031 instanceof Emp));
    }
}
