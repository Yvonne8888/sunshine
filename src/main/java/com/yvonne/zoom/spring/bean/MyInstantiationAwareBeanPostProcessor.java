package com.yvonne.zoom.spring.bean;


import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;

import java.beans.PropertyDescriptor;

/**
 * @author Yvonne Wang
 * @date 2021/04/08 13:59
 **/
public class MyInstantiationAwareBeanPostProcessor extends InstantiationAwareBeanPostProcessorAdapter {
    public MyInstantiationAwareBeanPostProcessor() {
        super();
        System.out.println("这是InstantiationAwareBeanPostProcessorAdapter实现类构造器！！");
    }

    /**
     * 接口方法、设置某个属性时调用。方法已过时
     * @param pvs
     * @param pds
     * @param bean
     * @param beanName
     * @return 回值也应该是PropertyValues对象
     * @throws BeansException
     */
//    @Override
//    public PropertyValues postProcessPropertyValues(PropertyValues pvs, PropertyDescriptor[] pds, Object bean, String beanName) throws BeansException {
//        System.out.println("InstantiationAwareBeanPostProcessor调用postProcessPropertyValues方法");
//        return pvs;
//    }

    /**
     * 接口方法、实例化Bean之前调用
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("InstantiationAwareBeanPostProcessor调用postProcessBeforeInstantiation方法");
        return null;
    }

    /**
     * 接口方法、实例化Bean之后调用。重写了BeanPostProcessor方法
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("InstantiationAwareBeanPostProcessor调用postProcessAfterInitialization方法");
        return bean;
    }
}
