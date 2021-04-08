package com.yvonne.basic;
import java.io.*;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

/**
 * JavaTest class
 *
 * @author Yvonne Wang
 *
 */
public class JavaTest {
    /**
     * java三大特征
     *  1）继承
     *  2）封装
     *  3）多态：
     *      方法重载（编译时的多态）的规则：
     *          1.方法名一致，参数列表中的参数顺序，类型，个数不同
     *          2.重载与方法的返回值无关，存在于父类与子类，同类中
     *          3.可以抛出不用的异常，可以有不同的修饰符
     *      方法重写（运行时的多态）的规则：
     *          1.参数列表必须完全与重写方法的一致
     *          2.在java 5之前返回类型需要与重写方法一致，java 7之后则可以不同，但是必须是父类返回值的派生类
     *          2.构造方法不能被重写，声明final与static的方法也不能被重写，但是static能够被再次声明
     *          3.访问权限不能比父类中被重写的方法的访问权限更低。比如父类的方法public，则子类重写该方法不能声明为protected
     *          4.重写的方法能够抛出任何非强制异常（UncheckException 运行时异常），无论被重写的方法是否抛出异常。
     *              重写的方法不能抛出新的强制性异常，或者比被重写方法声明的更广泛的强制性异常，反之则可以。
     */
    static class Demo{
        public static void main(String[] args) {
            //不能通过返回类型来区分重载，因为调用时不能指定类型信息，编译器不知道你要调用哪个函数。
            //比如void f()与int f(),如果调用int x = f(),则没有问题，调用f()则不知道具体调用哪个方法。我们关系的不是返回值，
            // 而是方法的其他效果。
            Dog dog = new Dog();
            dog.move(1);

            Animal dog1 = new Dog();
            dog1.move();

            //整型
            byte byteMinValue = Byte.MIN_VALUE;
            byte byteMaxValue = Byte.MAX_VALUE;
            System.out.println("byte范围：" + byteMinValue + "-" + byteMaxValue);
            short shortMinValue = Short.MIN_VALUE;
            short shortMaxValue = Short.MAX_VALUE;
            System.out.println("short范围：" + shortMinValue + "-" + shortMaxValue);
            int intMinValue = Integer.MIN_VALUE;
            int intMaxValue = Integer.MAX_VALUE;
            System.out.println("int范围：" + intMinValue + "-" + intMaxValue);
            long longMinValue = Long.MIN_VALUE;
            long longMaxValue = Long.MAX_VALUE;
            System.out.println("long范围：" + longMinValue + "-" + longMaxValue);

            //浮点型
            float floatMinValue = Float.MIN_VALUE;
            float floatMaxValue = Float.MAX_VALUE;
            System.out.println("float范围：" + floatMinValue + "-" + floatMaxValue);
            double doubleMinValue = Double.MIN_VALUE;
            double doubleMaxValue = Double.MAX_VALUE;
            System.out.println("double范围：" + doubleMinValue + "-" + doubleMaxValue);

            //char可以存储一个中文，范围0-65535
            //char字符型变量是存储Unicode编码的字符，Unicode编码字符集中包含了汉字。占两个字节
            char ch = '中';
            int max = Character.MAX_VALUE;
            int min = Character.MIN_VALUE;
            System.out.println("char可以存储一个中文：" + ch + ";char范围：" + min + "-" + max);

            //java 5之前switch(expr)只能是byte、short、char、int
            //java 5开始，引入enum枚举类型
            //java 7开始，引入String字符串类型
        }
        static class Animal{
            void move(){
                System.out.println("Animal move");
            }
        }
        static class Dog extends Animal {
            //重载
            void move(int a) {
                System.out.println("Dog move");
            }
            //重写
            @Override
            void move() {
                super.move();
                System.out.println("Dog move");
            }
        }

        //访问修饰符：public、protected、default、private
        public void accessModifier1(){};  //当前类、包、子类、其他类
        protected void accessModifier2(){}; //当前类、包、子类
        void accessModifier3(){};   //当前类、包
        private void accessModifier4(){};   //当前类

        String name = "成员变量，属于类";
        static String staticName = "类变量（静态变量），属于类，不属于类的任何一个对象，在内存中有且仅有一个拷贝";
        void f(){
            String name = "局部变量，属于方法";
        }

        final class FinalDemo{
            //final修饰的类不可继承
            final String string = "属性不可变";
            final void f1(){System.out.println("方法不可覆盖");}
        }

        //抽象类
        abstract static class AbstractDemo{
            //不能实例化，可以将抽象类作为引用类
            public AbstractDemo() {}    //构造器

            /**
             * 抽象方法。有抽象方法的类必须声明为抽象类，而抽象类不一定有抽象方法
             */
            abstract void abstractMethod();
            void method1() {}   //具体方法
            static void method2(){} //抽象类中可以包含静态方法，但是此类必须定义为静态抽象类，不能被子类重写。
            String name; //可以定义成员变量，修饰符不限制
        }
        class AbstractTest extends AbstractDemo{
            //一个类只能继承一个抽象类，需要实现所有的抽象方法，否则该类需要被声明未抽象类
            @Override
            void abstractMethod() {}
        }
        //接口
        interface InterfaceDemo{
            //不能定义构造器，不能实例化，可以将接口类型作为引用类,
            /**
             * 隐示abstract，接口中的方法全部都是抽象方法
             */
            void method1();
            public String NAME = "成员变量全部是public且为常量";
            /**
             * java 8之后中可以有static方法，但是不需要实现
             */
            static void f1(){};
        }
        interface InterfaceDemo1{
            /**
             * 只能为默认或者public
             */
            public void f();
        }
        class InterfaceTest implements InterfaceDemo,InterfaceDemo1 {
            //一个类可以实现多个接口，需要实现所有的抽象方法，否则该类需要被声明未抽象类
            @Override
            public void method1() {}
            @Override
            public void f() {}
        }

        //Exception：父类Throwable。编译时异常（CheckedException）和运行时异常（UnCheckedException）RunTimeException
        //Error：父类Throwable。
        class ThrowableDemo{
            void method1() throws Exception{
                try {
                }catch (Exception e){
                    throw e;
                }finally {
                    System.out.println("异常处理语句结构一部分，表示总是会被执行");
                }
            }
        }
    }

    /**
     * clone
     *  为对象分配内存，调用clone()方法时，分配的内存和原对象相同，然后再使用原对象中对应的各个域，填充新的域，填充完毕后clone方法返回，
     *  一个新的对象被创建，同样也可以把对象的引用发布到外部
     */
    static class CloneDemo{
        public static void main(String[] args) throws CloneNotSupportedException {
            //new一个String对象，为对象分配内存，再调用构造函数完成对象的初始化，构造函数返回后，一个对象创建完毕。
            // 将对象的引用（地址）发布到外部，在外部就可以对这个引用操作这个对象。
            //String是final类，不可以被继承（Is-A），最好的重用方式是关联关系（Has-A）和依赖关系（Use-A）。
            // String为引用类型，底层用char数组实现
            //StringBuffer/StringBuilder表示的字符串可以直接修改。StringBuilder在单线程下使用，没有被synchronized修饰，
            // 效率比StringBuffer高。
            String string = new String();
            string+="111";  //编译时将+转换为了StringBuilder

            CloneHead cloneHead = new CloneHead(18, "yvonne");
            //复制引用，两个对象地址一致
            CloneHead cloneHead1 = cloneHead;
            System.out.println("原对象地址：" + cloneHead);
            System.out.println("对象引用地址："+ cloneHead1);
            System.out.println("是否为同一个对象：" + (cloneHead == cloneHead1));
            System.out.println("----------------");
            //复制对象，两个对象地址不一致.此类调用clone()方法，对象需要实现Cloneable接口，否则报错CloneNotSupportedException
            CloneHead cloneHead2 = (CloneHead) cloneHead.clone();
            System.out.println("原对象地址：" + cloneHead);
            System.out.println("创建新的对象：" + cloneHead2);
            System.out.println("是否为同一个对象：" + (cloneHead == cloneHead2));
            //浅拷贝：在拷贝对象时，对基本数据类型的变量会重新复制一份，而对引用类型的变量只是对引用的拷贝，没有对引用指向的对象进行拷贝，
            //也就是说，两个对象内的引用类型变量仍然是指向同一块内存区域。
            String result = cloneHead.getName() == cloneHead2.getName() ? "浅拷贝":"深拷贝";
            System.out.println(result);
            System.out.println("----------------");
            //深拷贝：在拷贝对象时，会对引用指向的对象进行拷贝。与浅拷贝的区别就在于是否对对象中的引用变量所指向的对象进行拷贝。
            // 其本身以及内部的引用变量都需要实现Cloneable接口
            CloneBody cloneBody = new CloneBody(cloneHead);
            CloneBody cloneBody1 = (CloneBody) cloneBody.clone();
            System.out.println("原对象地址：" + cloneBody);
            System.out.println("创建新的对象：" + cloneBody1);
            System.out.println("是否为同一个对象：" + (cloneBody == cloneBody1));
            String result1 = cloneBody.getCloneHead() == cloneBody1.getCloneHead() ? "浅拷贝":"深拷贝";
            System.out.println(result1);
        }

        static class CloneHead implements Cloneable{
            private int age;
            private String name;
            //构造函数
            public CloneHead(int age, String name) {
                this.age = age;
                this.name = name;
            }

            public String getName() {
                return name;
            }

            @Override
            public Object clone() throws CloneNotSupportedException {
                return super.clone();
            }
        }
        static class CloneBody implements Cloneable{
            private int age;
            private String name;
            private CloneHead cloneHead;

            public CloneBody(CloneHead cloneHead) {
                this.cloneHead = cloneHead;
            }

            public CloneHead getCloneHead() {
                return cloneHead;
            }

            @Override
            public Object clone() throws CloneNotSupportedException {
                CloneBody cloneBody = (CloneBody) super.clone();
                cloneBody.cloneHead = (CloneHead) cloneHead.clone();
                return cloneBody;
            }
        }
    }

    /**
     * equals:
     *   (1)如果没有重写equals方法，如果两个对象的值相同，它们的hashCode值也一定相同。反之不一定。
     *   (2)如果想要hashCode相同，则需要重写equals与hashCode方法。
     *      如果重写equals方法，没有重写hashCode的方法，就会出现hashCode不相同。
     *      如果重写hashCode的方法，没有重写equals方法，就会出现equals不相同。
     *
     */
    static class EqualsDemo{
        public static void main(String[] args) {
            EqualsTest x = new EqualsTest("x");
            EqualsTest x1 = new EqualsTest("x");
            System.out.println("两个对象的值是否相同：" + (x.equals(x1) == true));
            System.out.println(x.hashCode());
            System.out.println(x1.hashCode());
            System.out.println("两个对象的hashCode是否相同：" + (x.hashCode()==x1.hashCode()));
        }
        static class EqualsTest{
            private String name;

            public EqualsTest(String name) {
                this.name = name;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            @Override
            public boolean equals(Object obj) {
                if (obj == null){
                    return false;
                }else {
                    if (obj instanceof EqualsTest){
                        EqualsTest equalsTest = (EqualsTest) obj;
                        if (equalsTest.getName() == this.getName()){
                            return true;
                        }
                    }
                }
                return false;
            }

            @Override
            public int hashCode() {
                final int prime = 31;
                int result = 1;
                result = prime * result + ((name == null) ? 0 : name.hashCode());
                return result;
            }
        }
    }

    /**
     * 操作符与控制执行流程
     */
    static class OperatorAndControlDemo{
        public static void main(String[] args) {
            String string = null;
            int num = 3;
            try{
                System.out.println("--------&与&&--------");
                //&逻辑与，左边string != null为false，还会继续判断右边表达式,右边表达式会报错NullPointerException
//                if (string != null & string.equals("")){}
            }catch (Exception e){}
            //&&短路与，左边string != null为false，右边表达式则短路不再继续
            if (string != null && !"".equals(string)){}

            System.out.println("--------break与continue--------");
            //break:跳出当前循环
            for (int i = 0; i < num; i++) {
                if (i==1){break;}
                System.out.println("break:" + i);
            }
            //continue:跳出本次循环，继续执行下次循环
            for (int i = 0; i < num; i++) {
                if (i==1){continue;}
                System.out.println("continue:" + i);
            }
        }
    }

    /**
     * 当一个对象被当作参数传递到一个方法后，此方法可改变这个对象的属性，并可返回变化后的结果，是值传递不是引用传递。
     *
     */
    static class PassByValueDemo{
        public static void main(String[] args) {
            //基本数据类型的传递。
            // 参数中传递的是基本类型num1，num2的拷贝，在函数中交换的也是那份拷贝的值，而不是数据本身。
            int num1 = 1;
            int num2 = 2;
            basicDateType(num1,num2);
            System.out.println("old num1:" + num1);
            System.out.println("old num2:" + num2);

            //引用数据类型的传递。
            // String是final不可改变，而数组可改变，StringBuffer也可以改变，改变的是其内容，不能改变外部变量所指向的内存地址。
            // 在方法中，传递引用数据类型int数组，实际上传递的是其引用count的拷贝，他们指向数组对象，在方法中可以改变数组对象的内容，
            // 即对复制的引用所调用的方法更改的是同一个对象。
            String str = new String("String");
            System.out.println("str.length方法：" + str.length());
            StringBuffer stringBuffer = new StringBuffer("StringBuffer");
            int[] count={1, 2, 3};
            System.out.println("count.length属性：" + count.length);
            referenceDateType(str, stringBuffer, count);
            System.out.println("old str:" + str);
            System.out.println("old change:" + stringBuffer);
            System.out.println("old count:" + Arrays.toString(count));

            //对象的引用是永远不会改变的。
            // 对象的引用指向的是ObjectType，而add方法中，传递的引用的一份副本(传的值)则指向了一个新的Object，并对其进行操作，
            // 而原来的对象并没有发生变化。引用指向的还是原来的对象。
            ObjectType objectType = new ObjectType();
            add(objectType);
            System.out.println(objectType.i);
        }

        private static void basicDateType(int num1, int num2) {
            int temp = num1;
            num1 = num2;
            num2 = temp;
            System.out.println("new num1:" + num1);
            System.out.println("new num2:" + num2);
        }

        private static void referenceDateType(String str, StringBuffer stringBuffer, int[] count) {
            str = "change String";
            stringBuffer.append(" change");
            count[0] = 6;
            System.out.println("new str:" + str);
            System.out.println("new change:" + stringBuffer);
            System.out.println("new count:" + Arrays.toString(count));
        }

        static class ObjectType{
            int i = 0;
        }
        private static void add(ObjectType objectType) {
            objectType = new ObjectType();
            objectType.i++;
        }
    }

    /**
     * 日期
     */
    static class DateDemo{
        public static void main(String[] args) {
            //获取年月日时分秒
            getDateMethod();
            //获取1970年1月1日0时0分0秒到现在的毫秒数
            getMillisecondMethod();
            //获取当月的第一天和最后一天
            getMonthFirstAndLastMethod();
            //获取昨天的当前日期
            getYesterdayMethod();

        }

        private static void getDateMethod() {
            Calendar calendar = Calendar.getInstance();
            String year = String.valueOf(calendar.get(Calendar.YEAR));
            String month = String.valueOf(calendar.get(Calendar.MONTH) + 1);//0-11
            String date = String.valueOf(calendar.get(Calendar.DATE));
            String hour = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
            String minute = String.valueOf(calendar.get(Calendar.MINUTE));
            String second = String.valueOf(calendar.get(Calendar.SECOND));
            System.out.println(year + "-" + month + "-" + date + " " + hour + ":" + minute + ":" + second);

            //Java 8 获取年月日时分秒
            LocalDateTime localDateTime = LocalDateTime.now();
            String year1 = String.valueOf(localDateTime.getYear());
            String month1 = String.valueOf(localDateTime.getMonthValue());//1-12
            String date1 = String.valueOf(localDateTime.getDayOfMonth());
            String hour1 = String.valueOf(localDateTime.getHour());
            String minute1 = String.valueOf(localDateTime.getMinute());
            String second1 = String.valueOf(localDateTime.getSecond());
            System.out.println(year1 + "-" + month1 + "-" + date1 + " " + hour1 + ":" + minute1 + ":" + second1);
        }
        private static void getMillisecondMethod() {
            long timeInMillis = Calendar.getInstance().getTimeInMillis();//第一种方式
            System.out.println(timeInMillis);
            long l = System.currentTimeMillis();//第二种方法
            System.out.println(l);
            //Java 8
            long millis = Clock.systemDefaultZone().millis();
            System.out.println(millis);
        }
        private static void getMonthFirstAndLastMethod() {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); //格式化日期
            //获取当月的第一天
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
//            calendar.add(Calendar.MONTH, 0);
//            calendar.set(Calendar.DAY_OF_MONTH, 1); //设置为1号，当前日期即为本月第一天
            Date time = calendar.getTime();
            String first = format.format(time);
            System.out.println("当月第一天的日期：" + first);

            //获取当月的最后一天
            Calendar calendar1 = Calendar.getInstance();
            calendar1.set(Calendar.DAY_OF_MONTH, calendar1.getActualMaximum(Calendar.DAY_OF_MONTH));
            Date time1 = calendar1.getTime();
            String last = format.format(time1);
            System.out.println("当月最后一天的日期：" + last);

            //Java 8
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); //格式化日期
            LocalDate now = LocalDate.now();
            //获取当月的第一天
            LocalDate firstDate = LocalDate.of(now.getYear(), now.getMonth(), 1);
            System.out.println("当月第一天的日期：" + firstDate);
            //获取当月的最后一天
            LocalDate lastDate = now.with(TemporalAdjusters.lastDayOfMonth());
            System.out.println("当月最后一天的日期：" + lastDate);
        }
        private static void getYesterdayMethod() {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, -1);
            System.out.println("昨天的当前时刻：" + calendar.getTime());

            //Java 8
            LocalDateTime localDateTime = LocalDateTime.now();
            LocalDateTime yesterday = localDateTime.minusDays(1);
            System.out.println("昨天的当前时刻：" + yesterday);
        }
    }

    /**
     * IO流
     */
    static class IoDemo{
        //字节输入流转字符输入流通过 InputStreamReader 实现，该类的构造函数可以传入 InputStream 对象。
        //字节输出流转字符输出流通过 OutputStreamWriter 实现，该类的构造函数可以传入 OutputStream 对象。
        //在 java 中能够被序列化的类必须先实现 Serializable 接口，该接口没有任何抽象方法只是起到一个标记作用。
        //当两个进程在进行远程通信时，彼此可以发送各种类型的数据。无论是何种类型的数据，都会以二进制序列的形式在网络上传送。
        //序列化：把Java对象转换为字节序列的过程。
        //反序列化：把字节序列恢复为Java对象的过程。
        public static void main(String[] args) throws Exception{

            //序列化对象。C:\java\workspace-idea表示存放序列化对象的文件。
            // 对象的默认序列化机制写入的内容是：对象的类，类签名，以及非瞬态和非静态字段的值。
            // 以下deposit为transient瞬态，序列化输入时为null
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("C:\\java\\workspace-idea\\objectFile.txt"));
            Customer customer = new Customer("Yvonne", 18, "1个亿");
            objectOutputStream.writeObject("您好！");  //写入字面值常量
            objectOutputStream.writeObject(new Date()); //写入匿名Date对象
            objectOutputStream.writeObject(customer);   //写入customer对象
            objectOutputStream.close();

            //反序列化对象。注意：读取的顺序需要与写入的顺序一致。
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("C:\\java\\workspace-idea\\objectFile.txt"));
            String readObject = (String) objectInputStream.readObject(); //读取字面值常量
            System.out.println("读取字面值常量：" + readObject);
            Date date = (Date) objectInputStream.readObject(); //读取匿名Date对象
            System.out.println("读取匿名Date对象：" + date);
            Customer customer1 = (Customer) objectInputStream.readObject(); //读取customer对象
            System.out.println("读取customer对象：" + customer1);
            objectInputStream.close();

        }
        static class Customer implements Serializable{
            private String name;
            private int age;
            private transient String deposit; //瞬态变量

            public Customer(String name, int age, String deposit) {
                this.name = name;
                this.age = age;
                this.deposit = deposit;
            }

            @Override
            public String toString() {
                return "Customer{" +
                        "name='" + name + '\'' +
                        ", age=" + age +
                        ", deposit='" + deposit + '\'' +
                        '}';
            }
        }

    }

    /**
     * 集合
     */
    static class CollectionDemo{
        public static void main(String[] args) {
            //List的三个实现类：
            // 1）ArrayList底层结构是数组，底层查询快，增删慢；
            // 2）LinkedList底层结构是链表型的，增删快，查询慢；
            // 3）vector底层机构是数组，线程安全，增删慢，查询慢。
            // 存储单列数据的集合，存储顺序有序，且可以重复

            //Set的两个实现类：
            // 1）HashSet底层结构是HashMap，不允许集合有重复值，需要重写equals()与hashCode()方法；
            // 2）LinkedSet继承HashSet，同时又基于LinkedHashMap实现，底层结构是LinkedHashSet。
            // 存储单列数据的集合，存储顺序无序，且不允许重复，但是元素在集合中的位置是由hashCode决定的，位置是固定的。

            //Map的三个实现类一个接口：
            // 1）HashMap基于Hash表的Map接口实现，非线程安全，高效，支持null值与null键
            // 2）LinkedHashMap是HashMap的一个子类，按照插入顺序
            // 3）HashTable线程安全，低效，不支持null值与null键
            // 4）SortMap接口，TreeMap实现类，能够按照键排序，默认是键值的升序排序
            // 存储键值双列数据的集合，存储顺序无序，且键不可以重复，值可以重复

            //使用HashMap排序。HashMap本身不可排序，可以使用LinkedHashMap，是有序链表结构。采用Collections集合工具类
            useHashMapSortMethod();

            //找出数组中重复value的个数
            findArrayRepeatCountMethod();

            //使用两个队列模拟堆栈结构
            //队列是先进先出，堆栈是先进后出。
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
            //利用HashMap记录每个字出现的次数
            HashMap<Integer, Integer> hashMap = new HashMap<>(16); //默认指定HashMap的大小为16
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

    /**
     * 内部类分为四种：
     *  1）成员内部类
     *  2）局部内部类
     *  3）匿名内部类
     *  4）静态内部类
     */
    static class InnerClassDemo{
        //成员内部类
        class InnerClass1{}
        //静态内部类
        static class InnerClass2{}
        //局部内部类
        void innerClassDemo(){
            class InnerClass2{}
        }
        //匿名内部类


    }




}











