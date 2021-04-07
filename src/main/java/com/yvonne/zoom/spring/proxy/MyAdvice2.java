package com.yvonne.zoom.spring.proxy;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

//定义切面
@Aspect
@Component
public class MyAdvice2 {
	/**
	 * 前置通知(Before)：是调用方法之前，调用
	 * 后置通过(AfterReturning)：在调用方法之后，调用（出现异常不调用 ）
	 * 环绕通知(Around):在调用方法的前后，都会执行
	 * 异常通知(After-Throwing):在方法调用出现异常时，执行
	 * 后置通知(After):无论是否出现异常都会 调用
	 */
	//下面利用一个空方法，将切入点抽取出来，后面调用时，直接使用Myadvice2.pc()即可调用切入点表达式
	@Pointcut("execution(* com.yvonne.zoom.spring.dao.impl.*DaoImpl.*(..))")
	public void pc(){

	}
	@Before("MyAdvice2.pc()")
	public void before(){
		System.out.println("前置通知.");
	}
	@AfterReturning("MyAdvice2.pc()")
	public void afterReturning(){
		System.out.println("后置通知，出现异常不调用.");
	}
	@Around("MyAdvice2.pc()")
	public Object around(ProceedingJoinPoint pjp) throws Throwable{
		System.out.println("环绕通知-前面代码");
		Object proceed = pjp.proceed();
		System.out.println("环绕通知-后面代码");
		return proceed;
	}
	@AfterThrowing("MyAdvice2.pc()")
	public void afterThrowing(){
		System.out.println("不得了了，出了异常了！");
	}
	@After("MyAdvice2.pc()")
	public void after(){
		System.out.println("无论是否出现异常，都会调用！");
	}
}
