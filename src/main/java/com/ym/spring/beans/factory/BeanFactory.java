package com.ym.spring.beans.factory;

import com.ym.spring.beans.BeansException;

/**
 * {@code BeanFactory}是IoC容器的核心接口, 用于定义获取Bean的方法
 *
 * @author YinMing
 * @since 2025/5/6 10:55
 */
public interface BeanFactory {

    Object getBean(String name) throws BeansException;

    <T> T getBean(String name, Class<T> requiredType) throws BeansException;

    <T> T getBean(Class<T> requiredType) throws BeansException;

    boolean containsBean(String name);
}
