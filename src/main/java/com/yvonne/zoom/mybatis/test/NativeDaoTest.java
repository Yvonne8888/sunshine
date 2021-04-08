package com.yvonne.zoom.mybatis.test;

import com.yvonne.zoom.mybatis.dao.IEmpDao;
import com.yvonne.zoom.mybatis.dao.impl.EmpDaoImpl;
import com.yvonne.zoom.mybatis.domain.Emp;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * @author Yvonne Wang
 * @date 2021/4/822:54
 */
public class NativeDaoTest {
    /**
     * 原始的Dao测试，相当于将XmlMappingTest的代码放到Dao内
     */
    private IEmpDao empDao;

    @Before
    public void init() {
        empDao = new EmpDaoImpl();
    }

    @Test
    //查询所有
    public void test01() {
        List<Emp> list = empDao.findAll("empMapper.findAll");
        for (Emp emp : list) {
            System.out.println(emp);
        }
    }

    @Test
    //模糊查询
    public void test02() {
        String fuzzy = "I";
        List<Emp> list = empDao.findEmpsByFuzzy("empMapper.findEmpsByFuzzy2", fuzzy);
        for (Emp emp : list) {
            System.out.println(emp);
        }
    }
}
