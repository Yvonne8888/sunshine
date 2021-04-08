package com.yvonne.advanced.design.pattern.structure;

/**
 * 桥接模式：
 *     将抽象化与实现化解耦，使得二者可以独立变化
 *     我们常用的JDBC桥DriverManager一样，JDBC进行连接数据库时，在各个数据库之间进行切换，基本不需要动太多的代码，甚至丝毫不动
 *     原因就是JDBC统一接口，每个数据库提供各自的实现，用一个叫做数据库驱动的程序来桥接
 */
public class Bridge {
    public static void main(String[] args) {
        SourceSub1 sourceSub1 = new SourceSub1();//实现类对象
        SourceSub2 sourceSub2 = new SourceSub2();//实现类对象
        MyBridge myBridge = new MyBridge();//功能桥
        myBridge.setSourceable3(sourceSub1);//桥接
        myBridge.method();//使用桥接后的方法
        myBridge.setSourceable3(sourceSub2);//桥接
        myBridge.method();//使用桥接后的方法
    }
}

//接口
interface Sourceable3{
    void method();
}

//实现类1
class SourceSub1 implements Sourceable3{
    @Override
    public void method() {
        System.out.println("This is SourceSub1 method!");
    }
}

//实现类2
class SourceSub2 implements Sourceable3{
    @Override
    public void method() {
        System.out.println("This is SourceSub2 method!");
    }
}

//定义一个抽象的桥
abstract class AbstractBridge{
    private Sourceable3 sourceable3;

    //桥接后实现动态方法调用
    public void method(){
        sourceable3.method();
    }

    public Sourceable3 getSourceable3() {
        return sourceable3;
    }

    public void setSourceable3(Sourceable3 sourceable3) {
        this.sourceable3 = sourceable3;
    }
}

//继承使用抽象的桥
class MyBridge extends AbstractBridge{
    @Override
    public void method(){
        getSourceable3().method();
    }
}


