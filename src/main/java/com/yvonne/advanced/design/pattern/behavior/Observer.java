package com.yvonne.advanced.design.pattern.behavior;

import java.util.Enumeration;
import java.util.Vector;

/**
 * 观察者模式：
 *     当一个对象变化时，其它依赖该对象的对象都会收到用纸，并且随着变化！
 *     对象之间是一（被依赖对象）对多（依赖对象）的关系
 */
public class Observer {
    public static void main(String[] args) {
        //观察者模式的使用
        Subject subject = new MySubject();
        subject.add(new Observer1());
        subject.add(new Observer2());
        subject.operation();
    }
}

//接口
interface IObserver{
    void update();
}

//依赖，实现类1
class Observer1 implements IObserver{
    @Override
    public void update() {
        System.out.println("Observer1 has received!");
    }
}

//依赖，实现类2
class Observer2 implements IObserver{
    @Override
    public void update() {
        System.out.println("Observer has received!");
    }
}

interface Subject{
    void add(IObserver observer);   //增加依赖对象
    void del(IObserver observer);   //删除依赖对象
    void notifyObserver();  //通知所有的依赖对象
    void operation();   //被依赖对象自身的操作
}

//被依赖，抽象类
abstract class AbstractSubject implements Subject{
    private Vector<IObserver> vector = new Vector<IObserver>(); //依赖关系一对多的对象池

    //增加依赖对象
    @Override
    public void add(IObserver observer){
        vector.add(observer);
    }

    //删除依赖对象
    @Override
    public void del(IObserver observer){
        vector.remove(observer);
    }

    //通知所有的依赖对象
    @Override
    public void notifyObserver(){
        Enumeration<IObserver> elements = vector.elements();
        while (elements.hasMoreElements()){
            elements.nextElement().update();
        }
    }
}

class MySubject extends AbstractSubject{
    @Override
    public void operation() {
        System.out.println("update self!");//操作自身
        notifyObserver();//通知所有
    }
}