package com.yvonne.advanced.design.pattern.create;

/**
 * 抽象工厂模式
 *     创建多个工厂类，无需修改之前的代码
 */
public class AbstractFactory {
    public static void main(String[] args) {
        SendMailFactory sendMailFactory = new SendMailFactory();
        Sender2 sendMail = sendMailFactory.produce();
        sendMail.Send();

        SendSmsFactory sendSmsFactory = new SendSmsFactory();
        Sender2 sendSms = sendSmsFactory.produce();
        sendSms.Send();
    }
}

//接口
interface Sender2{
    void Send();
}
//抽象的工厂接口
interface Provider{
    Sender2 produce();
}

//实现类
class MailSender2 implements Sender2{
    @Override
    public void Send() {
        System.out.println("This is mailSender2!");
    }
}
class SmsSender2 implements Sender2{
    @Override
    public void Send() {
        System.out.println("This is smsSender2!");
    }
}

//工厂类
class SendMailFactory implements Provider{
    @Override
    public Sender2 produce() {
        return new MailSender2();
    }
}
class SendSmsFactory implements Provider{
    @Override
    public Sender2 produce() {
        return new SmsSender2();
    }
}

