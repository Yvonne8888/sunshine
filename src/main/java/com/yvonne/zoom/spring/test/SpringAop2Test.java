package com.yvonne.zoom.spring.test;

import com.yvonne.zoom.spring.dao.IUserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @author Yvonne Wang
 * @date 2021/4/716:10
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext5.xml")
public class SpringAop2Test {

    @Resource(name="userDao")
    private IUserDao userDao;

    @Test
    public void test01() {
        userDao.add();  //非异常
        System.out.println("-----------------------");
        userDao.update();  //异常
    }
}
