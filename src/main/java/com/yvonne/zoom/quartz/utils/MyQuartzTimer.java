package com.yvonne.zoom.quartz.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Yvonne Wang
 * @date 2021/4/1120:51
 */
public class MyQuartzTimer {
    private static Logger logger = LoggerFactory.getLogger(MyQuartzTimer.class);
    private static int count;
    public void doTask(){
        try{
            synchronized (this){
                count++;
            }
            logger.info("第" + count + "次执行！当前时间为：" + new SimpleDateFormat("yyyyMMdd HH:mm:ss:SSS").format(new Date()));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
