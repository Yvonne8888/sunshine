package com.yvonne.advanced.design.pattern.behavior;

/**
 * 访问者模式：
 *     把数据结构和作用于结构上的操作解耦合，使得操作集合可相对自由地演化
 *     适用于数据结构相对稳定算法又易变化的系统。因为访问者模式使得算法操作增加变得容易
 *     优点是增加操作很容易，因为增加操作意味着增加新的访问者
 *     缺点就是增加新的数据结构很困难
 */
public class Visitor {
    public static void main(String[] args) {
        //访问者模式的使用
        MyVisitor1 myVisitor1 = new MyVisitor1();
        MyVisitor2 myVisitor2 = new MyVisitor2();
        MySubject2 mySubject2 = new MySubject2();
        mySubject2.accpet(myVisitor1);
        mySubject2.accpet(myVisitor2);
    }
}

//接口
interface IVisitor{
    void visit(Subject2 subject2);
}
interface Subject2{
    void accpet(IVisitor iVisitor);
    String getSubject();
}

//访问者1
class MyVisitor1 implements IVisitor{
    @Override
    public void visit(Subject2 subject2) {
        //访问动作
        System.out.println("more:" + subject2.getSubject());
    }
}
//访问者2
class MyVisitor2 implements IVisitor{
    @Override
    public void visit(Subject2 subject2) {
        //访问动作
        System.out.println("less:" + subject2.getSubject());
    }
}

//目标类
class MySubject2 implements Subject2{
    //接受将要访问它的访问者
    @Override
    public void accpet(IVisitor iVisitor) {
        iVisitor.visit(this);//给予访问动作
    }

    @Override
    public String getSubject() {
        return "nibi!";
    }
}

