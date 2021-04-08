package com.yvonne.zoom.mybatis.domain;


/**
 * 
 * @author Yvonne Wang
 * @date 2021/03/30 16:01
 **/
public class EmpX {
    private int empnoX;
    private String nameX;
    private int ageX;

    public EmpX() {
        super();
    }

    public EmpX(int empno, String name, int age) {
        this.empnoX = empno;
        this.nameX = name;
        this.ageX = age;
    }

    public int getEmpnoX() {
        return empnoX;
    }

    public void setEmpnoX(int empno) {
        this.empnoX = empno;
    }

    public String getNameX() {
        return nameX;
    }

    public void setNameX(String name) {
        this.nameX = name;
    }

    public int getAgeX() {
        return ageX;
    }

    public void setAgeX(int age) {
        this.ageX = age;
    }

    @Override
    public String toString() {
        return "EmpX{" +
                "empnoX=" + empnoX +
                ", nameX='" + nameX + '\'' +
                ", ageX=" + ageX +
                '}';
    }
}
