package com.ym.spring.beans.factory.support;

import com.ym.spring.beans.BeansException;
import com.ym.spring.beans.factory.config.BeanDefinition;

/**
 * @author YinMing
 * @since 2025/5/6 12:13
 */
public interface BeanDefinitionRegistry {

    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    boolean containsBeanDefinition(String beanName);

    String[] getBeanDefinitionNames();
}
