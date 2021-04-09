package com.yvonne.zoom.mybatis.test;

import com.yvonne.zoom.mybatis.domain.Dept;
import com.yvonne.zoom.mybatis.mapper.annotation.IDeptMapper;
import com.yvonne.zoom.mybatis.util.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * @author Yvonne Wang
 * @date 2021/4/90:14
 */
public class AnnotationMappingTest {
    /**
     * 通过注解将方法的实现与属性映射到接口上，在SqlMapConfig.xml文件内配置这个映射接口可以产生效果
     * 接口一般命名方式为实体类 + Mapper，不过名字自定义也没关系
     */

    @Test
    public void test01() {
        //查询所有
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        IDeptMapper deptMapper = sqlSession.getMapper(IDeptMapper.class);
        List<Dept> list = deptMapper.findAll();
        for (Dept dept : list) {
            System.out.println(dept);
        }
    }

    @Test
    public void test02() {
        //根据编号查询
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        IDeptMapper deptMapper = sqlSession.getMapper(IDeptMapper.class);
        Dept dept = deptMapper.findDeptByDeptno(2);
        System.out.println(dept);
    }

    @Test
    public void test03() {
        //根据编号和名字查询
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        IDeptMapper deptMapper = sqlSession.getMapper(IDeptMapper.class);
        Dept dept = deptMapper.findDeptByStmtIndex(2, "nina");
        System.out.println(dept);
    }

    @Test
    public void test04() {
        //新增数据
        SqlSession sqlSession = MyBatisUtils.getSqlSession(true);
        IDeptMapper deptMapper = sqlSession.getMapper(IDeptMapper.class);
        Dept dept = new Dept(3, "薯条", "333");
        deptMapper.save(dept);
        //sqlSession.commit();  //没有在getSqlSession()的时候传入true需要手动commit
    }

    @Test
    public void test05() {
        //删除数据1
        SqlSession sqlSession = MyBatisUtils.getSqlSession(true);
        IDeptMapper deptMapper = sqlSession.getMapper(IDeptMapper.class);
        int deptno = 3;
        deptMapper.remove(deptno);
        sqlSession.commit();  //没有在getSqlSession()的时候传入true需要手动commit
    }
}
