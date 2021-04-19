package com.yvonne.zoom.dom4j;

/**
 * @author Yvonne Wang
 * @date 2021/4/1920:55
 */
public class Student {
    private int sid;
    private String sname;
    private String sex;
    private int age;
    private String addr;

    public Student() {}

    public Student(int sid, String sname, String sex, int age, String addr) {
        this.sid = sid;
        this.sname = sname;
        this.sex = sex;
        this.age = age;
        this.addr = addr;
    }

    public int getSid() {
        return sid;
    }
    public void setSid(int sid) {
        this.sid = sid;
    }
    public String getSname() {
        return sname;
    }
    public void setSname(String sname) {
        this.sname = sname;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getAddr() {
        return addr;
    }
    public void setAddr(String addr) {
        this.addr = addr;
    }

    @Override
    public String toString() {
        return "sid:" + sid + " sname:" + " sex" + sex + " age" + age + " addr:" + addr;
    }

}
