package com.yvonne.advanced.design.pattern.behavior;

/**
 * 解释器模式：
 *     一般主要应用在OOP开发中的编译器开发中,所以适用面比较窄
 *     下面案例中利用表达式类与上下文类来进行计算
 */
public class Interpreter {
    public static void main(String[] args) {
        //解释器模式的使用
        Context2 con = new Context2(9,2);  //上下文存入9与2
        Plus3 plus3 = new Plus3();
        int value1 = plus3.interpreter(con);     //在表达式1中对上下文中9与2相加，得到值11
        Context2 con2 = new Context2(value1, 8); //上下文中存入11与8
        Miuns3 miuns3 = new Miuns3();
        int miuns = miuns3.interpreter(con2); //在表达式2中对上下文中的11与8相减，得到值3
        System.out.println(miuns);

        //以上简写
        int result = new Miuns3().interpreter(new Context2(new Plus3().interpreter(new Context2(9, 2)), 8));
        System.out.println(result);
    }
}

//接口
interface Expression{
    int interpreter(Context2 context2);
}

//实现的表达式1
class Plus3 implements Expression{
    @Override
    public int interpreter(Context2 context2) {
        return context2.getNum1() + context2.getNum2(); //进行上下文，值的运算
    }
}
//实现的表达式2
class Miuns3 implements Expression{
    @Override
    public int interpreter(Context2 context2) {
        return context2.getNum1() - context2.getNum2(); //进行上下文，值的运算
    }
}
//上下文，可以作为编译器
class Context2{
    private int num1;
    private int num2;

    public int getNum1() {
        return num1;
    }
    //可以在这个函数对num1进行翻译或者解释
    public void setNum1(int num1) {
        this.num1 = num1;
    }

    public int getNum2() {
        return num2;
    }
    //可以在这个函数对num2进行翻译或者解释
    public void setNum2(int num2) {
        this.num2 = num2;
    }

    public Context2(int num1, int num2) {
        this.num1 = num1;
        this.num2 = num2;
    }

}
