package com.ym.spring.beans.factory.config;

/**
 * @author YinMing
 * @since 2025/5/6 10:49
 */
public interface SingletonBeanRegistry {

    Object getSingleton(String beanName);

    void registerSingleton(String beanName, Object singletonObject);
}
