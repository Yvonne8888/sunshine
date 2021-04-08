package com.yvonne.zoom.spring.test;

import com.yvonne.zoom.spring.domain.Emp;
import com.yvonne.zoom.spring.domain.Student;
import com.yvonne.zoom.spring.service.IStudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.util.List;

/**
 * @author Yvonne Wang
 * @date 2021/04/06 10:53
 * Spring 注解
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext3.xml")
public class SpringAnnotationTest {
    /**
     * 从spring容器中取得JavaBean对象
     *  @Autowired+@Qualifier("指定bean的名称")配合使用
     *      @Autowired：是自动注入，自动从spring的上下文找到合适的bean来注入。如果有多个bean的类型一样，则不能满足要求。
     *  @Resource(name="指定bean的名称")
     *
     * 从spring容器中放入JavaBean对象
     *  @Service，@Controller，@Repository
     *      分别标记类是Service层类，Controller层类，Repository数据存储dao层的类，spring扫描注解配置时，会标记这些类要生成bean。
     *  @Component
     *      是一种泛指，标记类是组件，spring扫描注解配置时，会标记这些类要生成bean
     */

    /**
     * JavaBean作用范围的注解
     *  singleton(单例) prototype(多例)
     *  request（与web中request有同样的作用域，不常用）
     *  session（与web中session有同样的作用域，不常用）
     * @Scope(scopeName="prototype")
     */
    @Resource(name="studentService")
    private IStudentService studentService;
    @Resource(name="studentService")
    private IStudentService studentService1;
    @Test
    public void test() throws Exception{
        List<Student> students = studentService.findAll();
        for(Student student :students){
            System.out.println(student);
        }
        System.out.println(studentService == studentService1);
    }
    /**
     * 初始化/销毁的注解
     * @PostConstruct
     * @PreDestroy
     */
    @Resource(name="student")
    private Student student;
    @Test
    public void test1() throws Exception{
        System.out.println(student);
    }


}
