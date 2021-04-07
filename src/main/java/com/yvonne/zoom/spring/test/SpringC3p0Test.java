package com.yvonne.zoom.spring.test;

import java.util.List;

import javax.annotation.Resource;

import com.yvonne.zoom.spring.domain.Student;
import com.yvonne.zoom.spring.service.IStudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * @author Yvonne Wang
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext1.xml")
public class SpringC3p0Test {

	//方法一：使用ApplicationContext获取spring容器中的对象
	/*@Test
	public void testFindAll() throws Exception{
		ApplicationContext ac =
				new ClassPathXmlApplicationContext("applicationContext.xml");
		StudentService studentService =   (StudentService) ac.getBean("studentService");
		List<Student> students = studentService.findAll();
		for(Student student : students){
			System.out.println(student);
		}
	}*/
	//如下两组注解作用：从spring容器中取得某个javabean对象
	//第一组：@Autowired + @Qualifier("studentService1")
	//@Autowired     //默认情况下：此注解按照类型在spring容器中进行bean的查找，如果有多个bean的类型一样，哪么这个注解不能满足要求
	//@Qualifier("studentService1")  //在类型一致的情况下，指定同类型的某个bean的名称，这样就可以得到某个具体的bean了.
	//一般情况下，使用@Autowired与@Qualifier进行联合使用

	//第二组：@Resource(name="studentService1")
	//小结：第一组的使用与第二组的使用一样，但第二组简单，所以常用第二组
	@Resource(name="studentService1")
	private IStudentService studentService;
	@Test
	public void testFindAll() throws Exception {
		List<Student> students = studentService.findAll();
		for (Student student : students) {
			System.out.println(student);
		}

	}
}
