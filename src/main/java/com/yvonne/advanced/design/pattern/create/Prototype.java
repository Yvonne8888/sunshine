package com.yvonne.advanced.design.pattern.create;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 原型模式：
 *     将一个对象作为原型，对其进行复制、克隆，产生一个和原对象类似的新对象
 *     浅复制：将一个对象复制后，基本数据类型的变量都会重新创建，而引用类型，指向的还是原对象所指向
 *     深复制：将一个对象复制后，不论是基本数据类型还是引用类型，都会重新创建
 *             简单来说，就是深复制进行了安全彻底的复制，而浅复制不彻底
 */
public class Prototype {
    public static void main(String[] args) throws Exception {
        ClonePrototype clonePrototype = new ClonePrototype();
        SerializableObject serializableObject = new SerializableObject();
        clonePrototype.setObj(serializableObject);   //设置测试对象SerializableObject
        clonePrototype.setString("Nina");  //设置属性值
        ClonePrototype clone1 = clonePrototype.clone(); //浅复制1
        ClonePrototype clone2 = clonePrototype.clone(); //浅复制2

        //对比属性值
        System.out.println("--------浅复制--------");
        System.out.println("-->获取浅复制1的属性值：" + clone1.getString());
        clone2.setString("Yvonne"); //浅复制2的对象修改属性值
        System.out.println("-->获取浅复制2的属性值：" + clone2.getString());
        System.out.println("");
        
        //对比测试对象SerializableObject引用的指向
        ClonePrototype deepClone = clonePrototype.deepClone();  //深复制
        System.out.println("--------深复制--------");
        System.out.println("-->原有对象的指向（hashcode）：" + serializableObject); //测试对象SerializableObject的指向
        System.out.println("-->浅复制的指向（hashcode）：" + clone1.getObj());
        System.out.println("-->深复制的指向（hashcode）：" + deepClone.getObj());
        System.out.println("-->浅复制对象内的属性值：" + clone1.getObj().hello2);
        System.out.println("-->深复制对象内的属性值：" + deepClone.getObj().hello2);
    }
}

class ClonePrototype implements Cloneable, Serializable{

    private String string;

    private SerializableObject obj;

    //浅复制实现Cloneable，重写clone()
    @Override
    public ClonePrototype clone() throws CloneNotSupportedException {
        ClonePrototype clone = (ClonePrototype)super.clone();
        return clone;
    }

    //深复制实现Serializable读取二进制流
    public ClonePrototype deepClone() throws IOException, ClassNotFoundException {
        //写入当前对象的二进制流
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(this);

        //读取二进制流产生的新对象
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        return (ClonePrototype) objectInputStream.readObject();
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public SerializableObject getObj() {
        return obj;
    }

    public void setObj(SerializableObject obj) {
        this.obj = obj;
    }
}

//测试对象：实现了Serializable接口才能进行二进制流的深复刻
class SerializableObject implements Serializable{

    public String hello2 = "Hello!";
}