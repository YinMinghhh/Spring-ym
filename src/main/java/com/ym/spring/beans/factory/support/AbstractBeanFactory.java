package com.ym.spring.beans.factory.support;

import com.ym.spring.beans.BeansException;
import com.ym.spring.beans.factory.BeanFactory;
import com.ym.spring.beans.factory.config.BeanDefinition;

/**
 * @author YinMing
 * @since 2025/5/7 08:34
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    @Override
    public Object getBean(String name) throws BeansException {
        return doGetBean(name, null);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return doGetBean(name, requiredType);
    }

    @Override
    public <T> T getBean(Class<T> requiredType) throws BeansException {
        return getBean(requiredType.getName(), requiredType);
    }

    @Override
    public boolean containsBean(String name){
        return containsSingleton(name) || containsBeanDefinition(name);
    }

    protected <T> T doGetBean(String name, Class<T> requiredType) throws BeansException {
        Object bean = getSingleton(name);
        if (bean != null) {
            return (T) bean;
        }

        BeanDefinition beanDefinition = getBeanDefinition(name);
        return (T) createBean(name, beanDefinition, null);
    }

    protected abstract boolean containsBeanDefinition(String beanName);

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException;
}
