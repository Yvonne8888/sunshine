package com.yvonne.zoom.spring.utils;

/**
 * Spring
 *
 * @author Yvonne Wang
 */
public class SpringUtil {
    /**
     * Spring主要核心：
     *  1）控制反转IOC：传统的Java开发模式中，当需要一个对象时，我们会使用new或getInstance直接或者间接调用构造方法创建一个对象；
     *      而在Spring开发模式中，Spring容器使用工厂模式为我们创建了所需要的对象，不需要自己创建，直接调用Spring提供的对象即可。
     *      空构造方法创建单例对象
     *      静态工厂方法创建对象
     *      对象工厂方法创建对象
     *  2）依赖注入DI：Spring使用JavaBean对象的set方法或者带参的构造方法为我们在创建所需的对象时将其属性自动设置所需要的值的过程。
     *      set方法出入
     *      构造方法注入
     *      p名称空间注入（不常使用）
     *      spel注入
     *      数组注入
     *      List集合注入
     *      Map集合注入
     *      Properties集合注入
     *      对象工厂方法创建对象+spel注入
     *  3）面向切面编程AOP：在面向对象编程（OOP）思想中，我们将事物纵向抽成一个个对象；
     *      而在面向切面编程（AOP）思想中，我们将一个个的对象某些类似的方面横向抽成一个切面，对这个切面进行一些例如权限控制，事务管理，记录日志等
     *      公用操作处理的过程。
     *      AOP主要思想：横向重复，纵向抽取。
     *      AOP的底层是动态代理，如果是接口则采用的是JDK动态代理，如果是类采用CGLIB方式实现动态代理。
     */

    /**
     * Spring中的设计模式:
     *  1）单例模式：spring中两种代理方式，若目标对象实现了若干接口，spring使用jdk的java.lang.reflect.Proxy类代理。
     *      若目标兑现没有实现任何接口，spring使用CGLIB库生成目标类的子类。
     *      单例模式——在spring的配置文件中设置bean默认为单例模式。
     *  2）模板方式模式——用来解决代码重复的问题。
     *      比如：RestTemplate、JmsTemplate、JpaTemplate
     *  3）工厂模式——在工厂模式中，我们在创建对象时不会对客户端暴露创建逻辑，并且是通过使用同一个接口来指向新创建的对象。
     *      Spring中使用beanFactory来创建对象的实例。
     *  4）前端控制器模式——spring提供了前端控制器DispatherServlet来对请求进行分发。
     *  5）试图帮助（view helper）——spring提供了一系列的JSP标签，高效宏来帮助将分散的代码整合在试图中。
     *  6）依赖注入——贯穿于BeanFactory/ApplacationContext接口的核心理念。
     */

    /**
     * Spring与junit4整合
     *  注解：@RunWith(SpringJUnit4ClassRunner.class)
     *      @ContextConfiguration("classpath:applicationContext.xml")
     */

}
