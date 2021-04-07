package com.yvonne.advanced.design.pattern.behavior;

/**
 * 迭代器模式：
 *     顺序访问聚集中的对象，一般来说，集合中非常常见，如果对集合类比较熟悉的话，理解本模式会十分轻松
 *     这句话包含两层意思：一是需要遍历的对象，即聚集对象；二是迭代器对象，用于对聚集对象进行遍历访问
 */
public class IteratorPattern {
    public static void main(String[] args) {
        //迭代器模式的使用
        String[] strings = {"a","b","c","d","e"};
        MyCollection myCollection = new MyCollection(strings);
        IIterator iterator = myCollection.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}

//接口
interface Collection{
    IIterator iterator();
    Object get(int i);  //取得集合元素
    int size(); //取得集合大小
}

interface IIterator{
    Object previous(); //迁移
    Object next();  //后移
    boolean hasNext(); //是否存在此次迭代的值
    Object frist(); //取得第一个元素
}

//实现一个遍历字符串的聚集类
class MyCollection implements Collection{
    public String[] strings;//这里可以扩展到任何对象，作为泛型的一个新集合

    public MyCollection(String[] strings) {
        this.strings = strings;
    }

    @Override
    public IIterator iterator() {
        return new MyIterator(this);
    }

    @Override
    public Object get(int i) {
        return strings[i];
    }

    @Override
    public int size() {
        return strings.length;
    }
}

//实现一个迭代类
class MyIterator implements IIterator{
    private Collection collection;
    private int pos = -1; //从-1开始，因为后续next()函数需要有个+1操作，get()需要从0开始

    public MyIterator(Collection collection) {
        this.collection = collection;
    }

    @Override
    public Object previous() {
        if (pos > 0){
            pos--;//在上次迭代的基础上-1
        }
        return collection.get(pos);
    }

    @Override
    public Object next() {
        if (pos < collection.size()-1){
            pos++;//在上次迭代的基础上+1
        }
        return collection.get(pos);

    }

    @Override
    public boolean hasNext() {
        //只返回布尔值，不对迭代计数产生影响
        if (pos < collection.size()-1){
            return true;
        }else{
            return false;
        }
    }

    //使用此函数会重置迭代计数，计数为已经获得第一个元素基础上
    @Override
    public Object frist() {
        pos = 0;
        return collection.get(pos);
    }
}
