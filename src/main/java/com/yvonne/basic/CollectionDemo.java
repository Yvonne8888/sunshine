package com.yvonne.basic;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author ceshi
 * @Title:
 * @Package
 * @Description:
 * @date 2021/4/1712:26
 */
public class CollectionDemo {
    public static void main(String[] args) {
        //List的三个实现类：
        // 1）ArrayList底层结构是数组（动态数组），底层查询快，增删慢，不安全，使用频率很高。
        // 2）LinkedList底层结构是链表型（双向链表）的，增删快，查询慢，不安全。适合插入删除频繁的情况，内部维护了链表的长度。
        // 3）vector底层机构是数组，线程安全（所有方法加上synchronized），增删慢，查询慢。
        // 存储单列数据的集合，存储顺序有序，且可以重复。

        // 1.数组是定长的，往里添加数据，不会有问题吗？
            // 答：通过构造方法在初始化时指定数组的大小。通过无参构造方法进行初始化，则复制地城Object[]elementData为一个默认空数组，
            // 所以数组容量为0，只有对数组数据进行add添加时，才会分配默认初始容量为10；通过有参构造方法则会判断参数的大小为其分配容量。
        // 2.数组的长度有限，但是ArrayList可以存放任意数量的对象，长度不受限制，是怎么实现？
            // 答：通过数据扩容的方式去实现。
            // 比如：一个长度为10的数组，现在需要新增一个元素，发现满了，ArrayList会重新定义一个长度10+10/2的数组，然后把原数组的数据
            // 原封不动的复制到新数组中，再指向新数组
        // 3. 1.7之前初始化的容量为10，1.7即本身以后都是默认走空数组，只有第一次add时容量才是10
        // 4.为什么ArrayList增慢？
            // 答：有index新增也有直接新增，在新增之前会有一个长度的判断，如果长度不够需要扩容。
            // 比如长度为10的数组，在index(5)的位置新增一个元素，首先复制一个数组，将index(5)之后的元素放于新数组index(5+1)的位置，
            // 给需要新增的元素腾出位置，然后在index(5)的位置插入元素。慢的原因在于index(5)之后元素都需要复制，并且扩容。
        // 5.ArrayList(int initialCapacity)会不会初始化大小？
            // 答：会初始化数组的大小，List的大小没有变，通过size获取。
        ArrayList<Integer> list = new ArrayList<>(10);
        System.out.println("初始化数组的大小为10，list的大小等于：" + list.size());
        try {
            list.set(5,1);
            System.out.println("list set success");
        }catch (Exception e){
            System.out.println("list set exception");
        }
        // 6.ArrayList变为线程安全，Collections.synchronizedList
        Collections.synchronizedList(list);
        // 7.ArrayList适合做队列吗？
            // 答：不适合，队列是先进先出，如果使用ArrayList做队列，则需要在数组尾部追加数据，数组头部删除数据，反过来也可以，
            // 涉及到数组数据搬迁，这样太消耗性能。
        // 8.数组适合做队列吗？
            // 答：适合。比如：ArrayBlockingQueue内部实现就是一个环形队列，是一个定长队列，内部是用一个定长数组来实现的，先进先出，
            // 有界队列（即初始化时指定的容量，就是队列最大的容量，不会出现扩容，容量满，则阻塞进队操作；容量空，则阻塞出队操作）。
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue<>(2);
        arrayBlockingQueue.add(1);
        System.out.println(arrayBlockingQueue);
        // 9.ArrayList的遍历和LinkedList的遍历性能
            // 答：ArrayList快，优势在于内存的连续性
            // CPU的内存缓存结构会缓存连续的内存片段，可以大幅降低读取内存的性能开销。




        //Set的两个实现类：
        // 1）HashSet底层结构是HashMap，不允许集合有重复值，需要重写equals()与hashCode()方法；
        // 2）LinkedSet继承HashSet，同时又基于LinkedHashMap实现，底层结构是LinkedHashSet。
        // 存储单列数据的集合，存储顺序无序，且不允许重复，但是元素在集合中的位置是由hashCode决定的，位置是固定的。

        //Map的三个实现类一个接口：
        // 1）HashMap基于Hash表的Map接口实现，非线程安全，高效，支持null值与null键，数据结构：数组和链表组合
        // 2）LinkedHashMap是HashMap的一个子类，按照插入顺序
        // 3）HashTable线程安全，低效，不支持null值与null键
        // 4）SortMap接口，TreeMap实现类，能够按照键排序，默认是键值的升序排序
        // 存储键值双列数据的集合，存储顺序无序，且键不可以重复，值可以重复

        // 1.HashMap的数据结构，底层原理
            // 答：数据结构：数组和链表组合构成的。因为HashMap的位置都为空，在put插入的时候会根据key的hash去计算index。
            // 比如：put("yvonne",111)，插入了为yvonne的元素，这个时候通过哈希函数计算出插入的位置为2，那结果就如下
            // 0    1   2   3   4   5...
            //        yvonne            key
            //        111               value
        // 2.HashMap为啥需要链表
            // 答：数组的长度有限，在有限的长度里面使用哈希，哈希本身就存在概率性，就是put两个元素，
            // 该两个元素的hash有一定的概率会一样，这样子就会hash到一个值上，形成了链表。
            // 比如：
            // 0    1   2   3   4   5...
            //        yvonne            key
            //        111               value
            //        nina              key
            //        111               value
        // 3.Entry是怎么插入链表
            // 答：Java8之前为头插法，新来的值会取代原有的值，原有的值就顺推到链表中去，写这个代码的作者认为后来的值被查找的
            // 可能性更大一些，提升查找的效率。
            // 但是Java8之后为尾部插入，为什么？往一个容量大小为2put两个值，负载因子是0.75,0.75*2=1，在插入第二个就要resize，
            // 现在用不同线程插入ABC，数据都插入了，还没resize之前，链表指向可能是A->B->C，因为resize的赋值方式使用了
            // 单链表的头插方式，同一位置上新元素总会被放在链表的头部位置，在旧数组中同一条Entry链上的元素通过重新计算索引位置后，
            // 有可能放到了新数组的不同位置上，可能出现B->A，一旦几个线程都完成，可能出现环形链表，这个时候取值就有问题了。
            // 采用尾部插入在扩容是会保持链表元素原本的顺序
        // 4.HashMap的扩容机制
            // 答：数组容量是有限的，数据多次插入，到达一定数量则会进行扩容resize。
            // 扩容分为两步：第一步创建一个新的Entry空数组，长度是原数组的2倍，第二步遍历原Entry数据，把所有的Entry重新Hash到新数组
            // 为什么重新hash，不直接复制？以为长度扩大之后，Hash的规则也随之改变。
            // Hash的公式---> index = HashCode（Key） & （Length - 1）
            //  原来长度（Length）是8你位运算出来的值是2 ，新的长度是16你位运算出来的值明显不⼀样了。
        // 5.HashMap的默认初始化长度多少？
            // 答：16.在JDK1.8的236行有1<<4就是16。
            // 为什么不直接赋值16，而是采用位运算计算？在创建HashMao时。阿里巴巴规范插件会提醒我们赋初值，而且最好是2的幂，
            // 位与运算比算数计算的效率高，之所以选择16，是为了服务将Key映射到index的算法


        //使用HashMap排序。HashMap本身不可排序，可以使用LinkedHashMap，是有序链表结构。采用Collections集合工具类
        useHashMapSortMethod();

        //找出数组中重复value的个数
        findArrayRepeatCountMethod();

        //使用两个队列模拟堆栈结构
        //队列是先进先出（FIFO），堆栈是先进后出。
        simulationStackStructureMethod();


        //Collections工具类提供了相关的API，将不安全的集合变为安全,实际将核心方法添加上synchronized关键字
//            Collections.synchronizedCollection(collection);
//            Collections.synchronizedList(list);
//            Collections.synchronizedMap(map);
//            Collections.synchronizedSet(set);
    }

    private static void useHashMapSortMethod() {
        HashMap<Integer, User> users = new HashMap<>(3);
        users.put(1, new User("Yvonne", 18));
        users.put(2, new User("Nina", 18));
        users.put(3, new User("薯条", 6));
        System.out.println(users);

        //首先拿到map的键值对集合
        Set<Map.Entry<Integer, User>> entrySet = users.entrySet();
        //将Set集合转为List集合，为什么这样子做，为了使用工具类的排序方法
        List<Map.Entry<Integer, User>> list = new ArrayList<>(entrySet);
        //使用Collections集合工具类对list进行排序，排序规则使用匿名内部类实现
        Collections.sort(list, new Comparator<Map.Entry<Integer, User>>() {
            @Override
            public int compare(Map.Entry<Integer, User> o1, Map.Entry<Integer, User> o2) {
                //按照User的age由小到大的顺序
                return o1.getValue().getAge()-o2.getValue().getAge();
            }
        });
        //创建一个新的的有序HashMap子类的集合
        LinkedHashMap<Integer, User> linkedHashMap = new LinkedHashMap<>();
        //将List中的数据存储在LinkedHashMap中
        for(Map.Entry<Integer, User> entry : list){
            linkedHashMap.put(entry.getKey(), entry.getValue());
        }

        HashMap<Integer, User> hashMap = new LinkedHashMap<>(linkedHashMap);
        System.out.println(hashMap);
    }

    private static void findArrayRepeatCountMethod() {
        int[] arr = {1,3,4,1,4,5,8,2,3,1,4};
        // 利用HashMap记录每个字出现的次数
        // 默认指定HashMap的大小为16
        HashMap<Integer, Integer> hashMap = new HashMap<>(16);
        for (int i: arr) {
            //判断当前数字是否统计过，如果统计过，取出出现的次数并且+1
            Integer temp = hashMap.get(i);
            int sum = 0;
            if (temp != null){
                sum = temp.intValue();
            }
            Integer integer = new Integer(sum + 1);
            hashMap.put(i, integer);
        }
        for (Map.Entry<Integer, Integer> entry: hashMap.entrySet()) {
            Integer key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println(key + "出现次数：" + value);
        }
    }

    private static void simulationStackStructureMethod() {
        //队列queue1和队列queue2
        //入栈：queue1队列为空，queue2队列为空，将a,b,c,d依次入栈queue1
        //出栈：将queue1队列依次加入ArrayList集合中。以倒序的方法将集合中数据取出，放入queue2队列中
        Queue<String> queue1 = new LinkedList<>();
        Queue<String> queue2 = new LinkedList<>();
        ArrayList<String> arrayList = new ArrayList<>();
        queue1.offer("a");
        queue1.offer("b");
        queue1.offer("c");
        queue1.offer("d");
        System.out.print("入栈：");
        for (String str : queue1) {
            arrayList.add(str);
            System.out.print(str);
        }
        for (int i = arrayList.size()-1; i >= 0 ; i--) {
            queue2.offer(arrayList.get(i));
        }
        System.out.println("");
        System.out.print("出栈：");
        for (String str : queue2) {
            System.out.print(str);
        }
    }

    static class User {
        private String name;
        private int age;

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public int getAge() {
            return age;
        }
    }
}
