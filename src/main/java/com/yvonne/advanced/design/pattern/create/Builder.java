package com.yvonne.advanced.design.pattern.create;

import java.util.ArrayList;
import java.util.List;

/**
 * 建造者模式：
 *     工厂类模式提供的是创建单个类的模式，而建造者模式则是将各种产品集中起来进行管理，
 *     用来创建复合对象，所谓复合对象就是指某个类具有不同的属性，其实建造者模式就是前面抽象工厂模式和main测试结合
 */
public class Builder {
    public static void main(String[] args) {
        BuilderManage builderManage = new BuilderManage();
        List<Sender> senderList = builderManage.produceMailSender(5);
        senderList.add(new SmsSender());
    }
}

//创建实现了Sender接口的子类的集合
class BuilderManage{
    private List<Sender> list = new ArrayList<Sender>();

    public List<Sender> produceMailSender(int count){
        for (int i = 0; i < count; i++) {
            System.out.println(count);
            list.add(new MailSender());
        }
        return list;
    }

    public List<Sender> produceSmsSender(int count){
        for (int i = 0; i < count; i++) {
            System.out.println(count);
            list.add(new SmsSender());
        }
        return list;
    }
}