package com.yvonne.advanced.design.pattern.behavior;

import javax.swing.Icon;

/**
 * 命令模式：
 *     目的就是达到命令的发出者和执行者之间解耦，实现请求和执行分开
 *     举个例子，司令员下令让士兵去干件事情，从整个事情的角度来考虑，司令员的作用是发出口令，
 *     口令经过传递，传到了士兵，士兵去执行。这个过程好在，三者相互解耦，任何一方都不用去依赖其他人，
 *     只需要做好自己的事儿就好，司令员要的是结果，不会去关注士兵到底是怎么实现的
 */
public class Command {
    public static void main(String[] args) {
        //命令模式的作用
        Receiver receiver = new Receiver();
        MyCommand myCommand = new MyCommand(receiver); //设置接受者到第三方
        Invoker invoker = new Invoker(myCommand); //设置第三方到调用者
        invoker.action();
    }
}

//接口
interface ICommand{
    void execute();
}

//接收者
class Receiver{
    public void action(){
        System.out.println("command receiverd!");
    }
}

//传递命令的第三方
class MyCommand implements ICommand{
    private Receiver receiver;

    public MyCommand(Receiver receiver){
        this.receiver = receiver;
    }
    @Override
    public void execute() {
        System.out.println("第三方将命令传递给接收方！");
        receiver.action();
    }
}

//调用者
class Invoker{
    private MyCommand myCommand;

    public Invoker(MyCommand myCommand) {
        this.myCommand = myCommand;
    }

    public void action(){
        System.out.println("调用者将命令传递给第三方！");
        myCommand.execute();
    }
}
