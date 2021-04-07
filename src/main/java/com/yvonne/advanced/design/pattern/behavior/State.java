package com.yvonne.advanced.design.pattern.behavior;

/**
 * 状态模式：
 *     当对象的状态改变时，同事改变其行为！
 *     举例：QQ，有几种状态，在线、隐身、忙碌等，每个状态对应不同的操作，而且你的好友也能看到你的状态，
 *     所以，状态模式就两点：1.可以通过改变状态来获得不用的行为；2.你的好友能同时看到你的变化
 */
public class State {
    public static void main(String[] args) {
        //状态模式的使用
        MyState myState = new MyState();
        Context context = new Context(myState);
        myState.setValue("state1");//设置第一种状态
        context.method();//执行行为，行为会随状态变化而变化
        myState.setValue("state2");//设置第二种状态
        context.method();//执行行为，行为会随状态变化而变化
    }
}

//具备所有行为的状态类
class MyState{
    private String value;//状态类中具体的状态

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void method1(){
        System.out.println("execute the first option!");
    }

    public void method2(){
        System.out.println("execute the second option!");
    }
}

//切换行为的类
class Context{
    private MyState myState;

    public Context(MyState myState) {
        this.myState = myState;
    }

    public MyState getMyState() {
        return myState;
    }

    public void setMyState(MyState myState) {
        this.myState = myState;
    }

    //行为通过状态来改变
    public void method(){
        if ("state1".equals(myState.getValue())){
            myState.method1();
        }else if ("state2".equals(myState.getValue())){
            myState.method2();
        }
    }
}