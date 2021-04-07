package com.yvonne.zoom.spring.factory;

import com.yvonne.zoom.spring.domain.Emp;

/**
 * 
 * @author Yvonne Wang
 * @date 2021/03/30 15:58
 **/
public class EmpFactory {

    public static Emp getInstance(){
        System.out.println("使用静态工厂方法创建对象（工厂方法会在程序启动时被Spring自动加载）");
        return new Emp();
    }

    public Emp getEmp(){
        System.out.println("使用对象工厂方法创建对象（工厂方法会在程序启动时被Spring自动加载）");
        return new Emp();
    }
}
