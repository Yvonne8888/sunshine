package com.yvonne.advanced.design.pattern.behavior;

/**
 * 责任链模式：
 *     有多个对象，每个对象持有对下一个对象的引用，这样就会形成一条链，请求在这条链上传递，直到某一个对象决定处理该请求。
 *     但是发出者并不清楚到底最终哪个对象会处理该请求，所以，责任链模式可以实现在隐瞒客户端的情况下，对系统进行动态的调整。
 *     链接上的请求可以是一条链，可以是一个树，还可以是一个环，模式本身不约束这个，需要我们自己去实现，
 *     同时，在一个时刻，命令只允许由一个对象传给另一个对象，而不允许传给多个对象
 */
public class ChainOfResponsibility {
    public static void main(String[] args) {
        //责任链模式的使用
        MyHandler myHandler1 = new MyHandler("h1");//字符串来表达责任名
        MyHandler myHandler2= new MyHandler("h2");//字符串来表达责任名
        MyHandler myHandler3 = new MyHandler("h3");//字符串来表达责任名
        myHandler1.setHandler(myHandler2);//设置h1对h2负责
        myHandler2.setHandler(myHandler3);//设置h2对h3负责
        myHandler1.operator();//操作h1，h1对h2负责会操作h2，h2对h3负责会操作h3
    }
}

//接口
interface Handler{
    void operator();
}

//抽象类来实现责任关系
abstract class AbstractHander{
    private Handler handler;
    //得到责任
    public Handler getHandler() {
        return handler;
    }
    //设置责任
    public void setHandler(Handler handler) {
        this.handler = handler;
    }
}

//实现类来使用责任关系
class MyHandler extends AbstractHander implements Handler{
    private String name;

    public MyHandler(String name) {
        this.name = name;   //构造函数中设置责任名，这里是字符串
    }

    @Override
    public void operator() {
        //责任的操作
        System.out.println(name + " action!");
        Handler handler;
        if ((handler = getHandler())!=null){
            handler.operator();
        }
    }
}