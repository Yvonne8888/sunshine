package com.yvonne.zoom.mybatis.test;

import com.yvonne.zoom.mybatis.domain.Emp;
import com.yvonne.zoom.mybatis.domain.EmpX;
import com.yvonne.zoom.mybatis.util.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * @author Yvonne Wang
 * @date 2021/4/822:55
 */
public class XmlMappingTest {
    /**
     * 通过EmpMapping.xml映射Emp类，再将EmpMapper.xml配置到SqlMapConfig.xml文件中，通过SqlSession调用方法
     */
    @Test
    public void test01(){
        //获取SqlSession对象
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        //执行查询
        System.out.println("**********findAll()**********");
        List<Emp> list = sqlSession.selectList("empMapper.findAll");
        for (Emp emp : list) {
            System.out.println(emp);
        }

        System.out.println("**********findAllX()**********");
        List<EmpX> list2 = sqlSession.selectList("empMapper.findAllX");
        for (EmpX empX : list2) {
            System.out.println(empX);
        }

        System.out.println("**********findAllByMap()**********");
        List<EmpX> list3 = sqlSession.selectList("empMapper.findAllByMap");
        for (EmpX empX2 : list3) {
            System.out.println(empX2);
        }
    }

    /**
     * 模糊查询
     */
    @Test
    public void test02() {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();

        System.out.println("**********findEmpsByFuzzy1()**********");
        String fuzzy1 = "%n%";
        List<Emp> list1 = sqlSession.selectList("empMapper.findEmpsByFuzzy1", fuzzy1);
        for (Emp emp1 : list1) {
            System.out.println(emp1);
        }

        System.out.println("**********findEmpsByFuzzy2()**********");
        String fuzzy2 = "n";
        List<Emp> list2 = sqlSession.selectList("empMapper.findEmpsByFuzzy2", fuzzy2);
        for (Emp emp2 : list2) {
            System.out.println(emp2);
        }
    }

    /**
     * 新增
     */
    @Test
    public void test03() {
        SqlSession sqlSession = MyBatisUtils.getSqlSession(true);
        Emp emp = new Emp(3, "薯条", 6);
        sqlSession.update("empMapper.save", emp);
        System.out.println("新增成功");
        //sqlSession.commit();  //如果getSqlSession的时候没有传入参数，可以在末尾commit
    }

    /**
     * 删除
     */
    @Test
    public void test04() {
        SqlSession sqlSession = MyBatisUtils.getSqlSession(true);
        int empno = 3;
        sqlSession.update("empMapper.remove", empno);
        System.out.println("删除成功");
        //sqlSession.commit();  //如果getSqlSession的时候没有传入参数，可以在末尾commit
    }



}
