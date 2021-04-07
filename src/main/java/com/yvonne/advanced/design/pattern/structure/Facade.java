package com.yvonne.advanced.design.pattern.structure;

/**
 * 外观模式：
 *     解决类与类之间的依赖关系，像spring一样，可以将类和类之间的关系配置到配置文件中
 *     而外观模式就是将他们的关系放在一个外观类中，降低了类类之间的耦合度，该模式中没有涉及接口
 *     可以看做是加强版的代理模式，可以代理多个类
 */
public class Facade {
    public static void main(String[] args) {
        //外观模式的使用
        Computer computer = new Computer();
        computer.startup();
        System.out.println();
        computer.shutdown();
    }
}

//工具类1
class Cpu{
    void startup(){
        System.out.println("cpu startup!");
    }
    void shutdown(){
        System.out.println("cpu shutdown!");
    }
}
//工具类2
class Memory{
    void startup(){
        System.out.println("memory startup!");
    }
    void shutdown(){
        System.out.println("memory shutdown!");
    }
}
//工具类3
class Disk{
    void startup(){
        System.out.println("disk startup!");
    }
    void shutdown(){
        System.out.println("disk shutdown!");
    }
}

/**
 * 如果我们没有Computer类，那么Cpu/Memory/Disk他们之间将会互相持有实例，产生关系，
 * 这样回造成严重的依赖，修改一个类，可能会带来其他类的修改，这不是我们想要看到的
 * 有了Computer类，他们之间的关系被放在了Computer类里，这样就起到了解耦的作用
 * 这就是外观模式
 */
//工具包类
class Computer{
    private Cpu cpu;

    private Memory memory;

    private Disk disk;

    public Computer(){
        cpu = new Cpu();
        memory = new Memory();
        disk = new Disk();
    }

    public void startup(){
        System.out.println("start startup the computer!");
        cpu.startup();
        memory.startup();
        disk.startup();
        System.out.println("startup the computer end!");
    }

    public void shutdown(){
        System.out.println("start shutting down the computer");
        cpu.shutdown();
        memory.shutdown();
        disk.shutdown();
        System.out.println("shutdown the computer end!");
    }

}
