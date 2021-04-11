package com.yvonne.zoom.quartz.test;

import com.yvonne.zoom.quartz.utils.Hellojob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @author Yvonne Wang
 * @date 2021/4/1116:58
 */
public class JavaQuartzTest {
    public static void main(String[] args) throws SchedulerException {
        //1、调度器（Scheduler），从工厂中获取调度实例（默认：实例化new StdSchedulerFactory()）
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        //2、任务实例（jobDetail）。加载任务类，与Hellojob完成绑定，要求Hellojob实现Job接口
        JobDetail jobDetail = JobBuilder.newJob(Hellojob.class)
                //参数1：任务的名称（唯一实例）；参数2：任务组的名称
                .withIdentity("trigger1","group1")
                .build();
        //3、触发器（Trigger）
        Trigger trigger = TriggerBuilder.newTrigger()
                //参数1：c触发器的名称（唯一实例）；参数2：触发器组的名称
                .withIdentity("trigger1", "group1")
                //马上启动触发器
                .startNow()
                //每5s启动一次
                .withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(5))
                .build();
        //让调度器关联任务与触发器，保证按照触发器定义的条件执行任务
        scheduler.scheduleJob(jobDetail, trigger);
        //启动调度器
        scheduler.start();
    }
}
