package com.yvonne.zoom.spring.test;

import com.yvonne.zoom.spring.domain.Emp;
import com.yvonne.zoom.spring.service.IEmpService;
import com.yvonne.zoom.spring.service.IEmpTransferService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.sql.*;
import java.util.List;

/**
 * @author Yvonne Wang
 * @date 2021/4/70:54
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext7.xml")
//@ContextConfiguration("classpath:applicationContext6.xml")  //在7中已经导入了6，直接使用7
//@ContextConfiguration("classpath:applicationContext*.xml")  //如果7中没有导入6，可以写成*
public class SpringJdbcTemplateTest {
    @Resource
    private IEmpService empService2;

    @Resource
    private IEmpService empService3;

    @Resource(name="empService3")
    private IEmpTransferService empTransferService;

    @Resource
    private IEmpTransferService empTransferService2;

    @Test
    public void test01() {
        List<Emp> list = empService2.findAll();
        System.out.println("TEST01-----------------------------");
        for (Emp emp : list) {
            System.out.println(emp);
        }
    }

    @Test
    public void test02() {
        List<Emp> list = empService3.findAll();
        System.out.println("TEST02-----------------------------");
        for (Emp emp : list) {
            System.out.println(emp);
        }
    }

    @Test
    //XML注入方式：测试利用事务转账（可以手动在Dao层增加异常来测试事务）
    public void test03() {
        System.out.println(empService2.findEmpByNo(1));
        System.out.println(empService2.findEmpByNo(2));
        boolean flag = empTransferService.transfer(2, 1, 100);
        System.out.println("转账是否成功：" + flag);
        System.out.println(empService2.findEmpByNo(1));
        System.out.println(empService2.findEmpByNo(2));
    }

    @Test
    //注解方式：测试利用事务转账（可以手动在Dao层增加异常来测试事务）
    public void test04() {
        System.out.println(empService2.findEmpByNo(1));
        System.out.println(empService2.findEmpByNo(2));
        boolean flag = empTransferService2.transfer(2, 1, 100);
        System.out.println("转账是否成功：" + flag);
        System.out.println(empService2.findEmpByNo(1));
        System.out.println(empService2.findEmpByNo(2));
    }

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try{
            // 注册 JDBC 驱动
            Class.forName("com.mysql.jdbc.Driver");

            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sunshine","root","123456");

            // 执行查询
            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM emp";
            ResultSet rs = stmt.executeQuery(sql);

            // 展开结果集数据库
            while(rs.next()){
                // 通过字段检索
                int id  = rs.getInt("empno");
                String name = rs.getString("name");
                int age = rs.getInt("age");

                // 输出数据
                System.out.print("id: " + id);
                System.out.print(", name: " + name);
                System.out.print(", age: " + age);
                System.out.print("\n");
            }
            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) {
                    conn.close();
                }
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
    }

}
