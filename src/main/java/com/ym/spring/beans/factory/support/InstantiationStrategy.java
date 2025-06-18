package com.ym.spring.beans.factory.support;

import com.ym.spring.beans.BeansException;
import com.ym.spring.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * 负责创建与根 Bean 定义对应的实例的接口
 *
 * <p>由于存在多种实例化方法(包括使用 CGLIB 动态创建子类以支持方法注入), 因此将其提取到策略中.
 *
 * @author YinMing
 * @since 2025/6/18 15:09
 */
public interface InstantiationStrategy {

    /**
     * 实例化Bean
     * @param beanDefinition bean定义
     * @param beanName bean名称
     * @param ctor 构造方法, null表示使用默认构造
     * @param args 构造方法参数列表, null或空数组表示无参数
     * @return 实例化的bean对象
     * @throws BeansException 如果实例化失败
     */
    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor<?> ctor, Object[] args) throws BeansException;
}
