package com.yvonne.advanced.design.pattern.behavior;

/**
 * 中介者模式：
 *     中介者模式也是用来降低类类之间的耦合，因为如果类类之间有依赖关系的话，不利于功能的拓展和维护，
 *     因为只要修改一个对象，其它关联的对象都得进行修改。
 *     只需关系被操作对象和中介者的关系，具体类类之间的关系及调度交给中介者就行，有点像spring容器的作用
 */
public class Mediator {
    public static void main(String[] args) {
        //中介者模式的使用
        IMediator myMediator = new MyMediator();
        myMediator.createMediator();
        myMediator.workAll();
    }
}

interface IMediator{
    void createMediator();
    void workAll();
}

//抽象的基类
abstract class AbstractUser{
    private IMediator iMediator; //对象中包含中介者

    public AbstractUser(IMediator iMediator) {
        this.iMediator = iMediator; //需要中介者类来构造函数
    }

    public IMediator getiMediator(){
        return iMediator;
    }

    //统一抽象的动作
    public abstract void work();
}

class User1 extends AbstractUser{
    public User1(IMediator iMediator) {
        super(iMediator);
    }

    //实现抽象的动作
    @Override
    public void work() {
        System.out.println("user1 execute!");
    }
}

class User2 extends AbstractUser{
    public User2(IMediator iMediator) {
        super(iMediator);
    }

    //实现抽象的动作
    @Override
    public void work() {
        System.out.println("user2 execute!");
    }
}

//中介者类，实现统一接口，持有统一操作的动作
class MyMediator implements IMediator{
    private AbstractUser user1;  /**中介者中包含对象*/
    private AbstractUser user2;  /**中介者中包含对象*/

    public AbstractUser getUser1() {
        return user1;
    }

    public AbstractUser getUser2() {
        return user2;
    }

    @Override
    public void createMediator() {
        user1 = new User1(this);//将中介者传入对象内，中介者本身持有此对象
        user2 = new User2(this);//将中介者传入对象内，中介者本身持有此对象
    }

    @Override
    public void workAll() {
        user1.work();
        user2.work();
    }
}
