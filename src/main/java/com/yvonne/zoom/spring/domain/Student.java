package com.yvonne.zoom.spring.domain;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author Yvonne Wang
 */
@Component("student")
public class Student {
	private int empno;
	private String name;
	private int age;

	public Student() {
	}

	public Student(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public Student(int empno, String name, int age) {
		this.empno = empno;
		this.name = name;
		this.age = age;
	}

	public int getEmpno() {
		return empno;
	}

	public void setEmpno(int empno) {
		this.empno = empno;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Student{" +
				"empno=" + empno +
				", name='" + name + '\'' +
				", age=" + age +
				'}';
	}

	/**
	 * 初始化注解
	 */
	@PostConstruct
	public void init(){
		System.out.println("Student→init().");
	}

	/**
	 * 销毁注解
	 */
	@PreDestroy
	public void destroy(){
		System.out.println("Student→destroy().");
	}
}
