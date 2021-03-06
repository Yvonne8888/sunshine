package com.yvonne.zoom.mybatis.domain;

import com.yvonne.zoom.mybatis.domain.Emp;

import java.util.List;

/**
 * @author Yvonne Wang
 * @date 2021/4/923:55
 */
public class Dept {
    private int deptno;
    private String dnameX;
    private String locX;

    /**
     * 在XML映射文件里面通过resultMap中association配置关联查询出另外表的数据
     */
    private Emp emp;

    /**
     * 一对多的关系
     */
    private List<Emp> emps;

    public Dept() {
        super();
    }
    public Dept(int deptno, String dnameX, String locX) {
        super();
        this.deptno = deptno;
        this.dnameX = dnameX;
        this.locX = locX;
    }
    public Dept(int deptno, String dnameX, String locX, Emp emp) {
        super();
        this.deptno = deptno;
        this.dnameX = dnameX;
        this.locX = locX;
        this.emp = emp;
    }

    public List<Emp> getEmps() {
        return emps;
    }
    public void setEmps(List<Emp> emps) {
        this.emps = emps;
    }
    public int getDeptno() {
        return deptno;
    }
    public void setDeptno(int deptno) {
        this.deptno = deptno;
    }
    public String getDnameX() {
        return dnameX;
    }
    public void setDnameX(String dnameX) {
        this.dnameX = dnameX;
    }
    public String getLocX() {
        return locX;
    }
    public void setLocX(String locX) {
        this.locX = locX;
    }
    public Emp getEmp() {
        return emp;
    }
    public void setEmp(Emp emp) {
        this.emp = emp;
    }

    @Override
    public String toString() {
        return "Dept [deptno=" + deptno + ", dnameX=" + dnameX + ", locX=" + locX + ", emp=" + emp + ", emps=" + emps
                + "]";
    }
}
