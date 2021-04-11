package com.yvonne.zoom.mybatis.domain;


/**
 * 
 * @author Yvonne Wang
 * @date 2021/03/30 16:01
 **/
public class Emp {
    private int empno;
    private String name;
    private int age;
    private int deptpno;

    private Dept dept;

    public Emp() {
    }

    public Emp(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Emp(int empno, String name, int age) {
        this.empno = empno;
        this.name = name;
        this.age = age;
    }

    public Emp(int empno, String name, int age, int deptpno, Dept dept) {
        this.empno = empno;
        this.name = name;
        this.age = age;
        this.deptpno = deptpno;
        this.dept = dept;
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

    public int getDeptpno() {
        return deptpno;
    }

    public void setDeptpno(int deptpno) {
        this.deptpno = deptpno;
    }

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "empno=" + empno +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
