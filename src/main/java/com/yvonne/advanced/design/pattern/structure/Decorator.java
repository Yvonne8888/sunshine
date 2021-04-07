package com.yvonne.advanced.design.pattern.structure;

/**
 * 装饰器模式
 *     给一个对象动态增加一些新的功能，也能动态撤销（继承不能做到这点是因为继承的功能是静态的，不能动态增删）
 *     要求装饰对象与被装饰对象实现同一个接口，装饰对象持有被装饰对象的实例
 */
public class Decorator {
    public static void main(String[] args) {
        //装饰器的使用
        Sourceable source2 = new Source2();
        source2.method();//原始方法
        MyDecorator myDecorator = new MyDecorator(source2);
        myDecorator.method();//被装饰以后的方法
    }
}

//接口
interface Sourceable{
    void method();
}

//被装饰类
class Source2 implements Sourceable{
    @Override
    public void method() {
        System.out.println("This is Source2 method!");
    }
}

//装饰类
class MyDecorator implements Sourceable{
    private Sourceable sourceable;

    public MyDecorator(Sourceable sourceable){
        super();
        this.sourceable = sourceable;
    }

    @Override
    public void method() {
        System.out.println("Before decorator!");
        sourceable.method();
        System.out.println("After decorator!");

    }
}