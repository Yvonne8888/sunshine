package com.yvonne.zoom.quartz.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * @author Yvonne Wang
 * @date 2021/4/1120:49
 */
public class SpringQuartzTest {
    private static Logger logger = LoggerFactory.getLogger(SpringQuartzTest.class);
    public static void main(String[] args){
        logger.info("将要启动定时器---------");
        try{
            new FileSystemXmlApplicationContext(ClassLoader.getSystemResource("quartz_timer.xml").getFile());
        }catch (Exception e){
            e.printStackTrace();
        }
        logger.info("将要结束定时器---------");
    }
}
