package com.ym.spring.beans.factory.support;

import com.ym.spring.beans.BeansException;
import com.ym.spring.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * BeanFactory中使用的简单实例化方法
 *
 * <p>直接用反射实现的
 *
 * @author YinMing
 * @since 2025/6/18 15:26
 */
public class SimpleInstantiationStrategy implements InstantiationStrategy {

    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor<?> ctor, Object[] args) throws BeansException {
        Class<?> clazz = beanDefinition.getBeanClass();
        try {
            if (ctor != null) {
                args = args == null ? new Object[0] : args;

                if (args.length != ctor.getParameterCount()) {
                    throw new BeansException("Parameter count does not match: " + args.length + ", but expected: " + ctor.getParameterCount());
                }

                return ctor.newInstance(args);
            } else {
                return clazz.getDeclaredConstructor().newInstance();
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new BeansException("Fail to instantiate bean [" + beanName + "]", e);
        }
    }
}
