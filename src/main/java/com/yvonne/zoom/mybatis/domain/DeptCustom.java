package com.yvonne.zoom.mybatis.domain;

/**
 * @author Yvonne Wang
 * @date 2021/4/100:03
 */
public class DeptCustom extends Dept{
    //去掉与父类中重复的字段（一对一关系）
    //private int deptno;

    private String info;

    public String getInfo() {
        return info;
    }
    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return super.toString() + "\t" + "DeptCustom [info=" + info + "]";
    }

}
