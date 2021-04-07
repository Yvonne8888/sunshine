package com.yvonne.advanced.design.pattern.structure;


/**
 * 适配器模式：
 *     将某个类的接口转换成客户端期望的另一个接口表示，目的是消除由于接口不匹配所造成的类的兼容性问题
 *     1.类的适配器模式
 *     2.对象的适配器模式
 *         装饰模式、代理模式、外观模式、桥接模式、组合模式、亨元模式，这六个模式起源于对象的适配器模式
 *     3.接口的适配器模式
 */
public class Adapter {
    public static void main(String[] args) {
        //类的适配器模式的使用
        Targetable targetable = new ClassAdapter();
        targetable.method1(); //拓展的Source类的方法
        targetable.method2(); //接口的方法

        //对象的适配器模式的使用
        Targetable targetable2 = new ObjectAdapter(new Source());
        targetable2.method1(); //拓展的Source类的方法
        targetable2.method2(); //接口的方法

        //接口的适配器模式的使用
        Targetable targetable3 = new InterfaceAdapter1();
        targetable3.method1();  //重写的抽象类method1()
        targetable3.method2();  //抽象类的method2() 无具体实现
        Targetable targetable4 = new InterfaceAdapter2();
        targetable4.method1();  //抽象类的method1() 无具体实现
        targetable4.method2();  //重写的抽象类method2()
    }
}

//Source类拥有一个方法，待适配目标接口Targetable
class Source{
    public void method1(){
        System.out.println("This is source method1!");
    }
}
//接口
interface Targetable{
    //与原类Source中的方法相同
    void method1();
    //新类的方法
    void method2();
}

/**
 * 1.类的适配器模式
 * 通过继承Source类来实现Targetable
 */
class ClassAdapter extends Source implements Targetable{
    @Override
    public void method2() {
        System.out.println("This is targetable method2!");
    }
}

/**
 * 2.对象的适配器模式
 * 在类的适配器模式基础，修改为使用Source的实例
 */
class ObjectAdapter implements Targetable{
    private Source source;

    public ObjectAdapter(Source source){
        super();
        this.source = source;
    }

    @Override
    public void method1() {
        source.method1();
    }

    @Override
    public void method2() {
        System.out.println("This is targetable method2!");
    }
}

/**
 * 3.接口的适配器模式
 * 当不希望实现Targetable接口中所有的方法时，继承实现了该接口所有的抽象类AbstractTargetable来实现指定的方法
 */
//抽象类实现了Targetable接口的所有方法
abstract class AbstractTargetable implements Targetable{
    //无需写具体实现
    @Override
    public void method1(){
        System.out.println("This is the AbstractTargetable method1!");
    };
    @Override
    public void method2(){
        System.out.println("This is the AbstractTargetable method2!");
    };
}

class InterfaceAdapter1 extends AbstractTargetable{
    @Override
    public void method1(){
        System.out.println("This is the InterfaceAdapter1 method1!");
    };
}
class InterfaceAdapter2 extends AbstractTargetable{
    @Override
    public void method2(){
        System.out.println("This is the InterfaceAdapter2 method2!");
    };
}