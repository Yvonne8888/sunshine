package com.yvonne.advanced.design.pattern.create;

/**
 * 工厂方法模式：
 *     1.普通工厂模式
 *     2.多个工厂模式
 *     3.静态工厂模式（大多数选择）
 * 缺点：
 *     类的创建依赖工厂类，也就是说，如果想要拓展程序，必须对工厂类进行修改，这违背了闭包原则。
 * 解决：
 *     抽象工厂模式
 */
public class FactoryMethod {
    public static void main(String[] args) {
        //普通工厂的使用
        OrdinarySendFactory ordinarySendFactory = new OrdinarySendFactory();
        Sender sender = ordinarySendFactory.product("sms");
        sender.send();

        //多个工厂的使用
        MultipleSendFactory multipleSendFactory = new MultipleSendFactory();
        Sender productMail = multipleSendFactory.productMail();
        productMail.send();

        //静态工厂模式
        Sender productSms = StaticSendFactory.productSms();
        productSms.send();
    }
}

/**
 * 1.普通工厂模式
 *   建立一个工厂类，对实现了同一接口的一些类进行实例的创建。
 */
//接口
interface Sender{
    void send();
}
//实现类
class MailSender implements Sender{
    @Override
    public void send(){
        System.out.println("This is MailSender!");
    }
}
class SmsSender implements Sender{
    @Override
    public void send(){
        System.out.println("This is SmsSender!");
    }
}
//建立工厂
class OrdinarySendFactory{
    public Sender product(String type){
        switch (type){
            case "mail":
                return new MailSender();
            case "sms":
                return new SmsSender();
            default:
                System.out.println("Please enter the correct type!");
                return null;
        }
    }
}

/**
 * 2.多个工厂模式
 *   该模式是对普通工厂方法模式的改进，在普通工厂方法模式中，如果传递的字符串出错，则不能正确创建对象，
 *   而多个工厂方法模式是提供多个工厂方法，分别创建对象。
 */
class MultipleSendFactory{
    //改进工厂返回接口申明的实现类
    public Sender productMail(){
        return new MailSender();
    }
    public Sender productSms(){
        return new SmsSender();
    }
}

/**
 * 3.静态工厂模式
 *  将多个工厂方法模式里的方法置为静态的，不需要创建实例，直接调用即可。
 */
class StaticSendFactory{
    public static Sender productMail(){
        return new MailSender();
    }
    public static Sender productSms(){
        return new SmsSender();
    }
}

