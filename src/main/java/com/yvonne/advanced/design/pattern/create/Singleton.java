package com.yvonne.advanced.design.pattern.create;

/**
 * 单例设计模式
 *     饿汉式：加载类的时候就创建了对象。线程安全
 *     懒汉式：需要用时，才会创建对象。线程不安全（synchronized加锁）
 *
 */
public class Singleton {
    public static void main(String[] args) {
        System.out.println("--------饿汉式单例模式--------");
        System.out.println("第一次取得实例");
        HungryChinese hungryChinese = HungryChinese.getInstance();
        System.out.println("第二次取得实例");
        HungryChinese hungryChinese1 = HungryChinese.getInstance();
        if (hungryChinese == hungryChinese1){
            System.out.println(">>>>hungryChinese,hungryChinese1为同一实例<<<<");
        }

        System.out.println("");

        System.out.println("--------懒汉式单例模式--------");
        System.out.println("第一次取得实例");
        LazyMan lazyMan = LazyMan.getInstance();
        System.out.println("第二次取得实例");
        LazyMan lazyMan1 = LazyMan.getInstance();
        if (lazyMan == lazyMan1){
            System.out.println(">>>>lazyMan,lazyMan1为同一实例<<<<");
        }

    }
}

//饿汉式
class HungryChinese{
    //创建对象
    private static HungryChinese hungryChinese = new HungryChinese();
    //无参构造函数,声明private很关键
    private HungryChinese(){
        System.out.println("-->开始调用构造函数");
    }

    public static HungryChinese getInstance(){
        System.out.println("-->开始调用公有方法返回实例");
        return hungryChinese;
    }
}

//懒汉式
class LazyMan{
    //创建对象
    private static LazyMan lazyMan = null;
    //无参构造函数,声明private很关键
    private LazyMan(){
        System.out.println("-->开始调用构造函数");
    }

    public synchronized static LazyMan getInstance(){
        System.out.println("-->开始调用公有方法返回实例");
        if (lazyMan == null){
            System.out.println("-->构造函数的实例当前并没有被创建");
            try {
                //线程休眠
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lazyMan = new LazyMan();
        }else{
            System.out.println("-->构造函数的实例已经被创建");
        }
        System.out.println("-->方法调用结束，返回单例");
        return lazyMan;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    LazyMan lazyMan = new LazyMan();
                    LazyMan manInstance = lazyMan.getInstance();
                    System.out.println(manInstance.hashCode());
                }
            }).start();
        }
    }
}


