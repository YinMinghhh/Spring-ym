package com.ym.spring.beans.factory.support;

import com.ym.spring.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * @author YinMing
 * @since 2025/5/6 11:19
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    private final Map<String, Object> singletonObjects = new HashMap<>();

    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

    @Override
    public void registerSingleton(String beanName, Object singletonObject) {
        singletonObjects.put(beanName, singletonObject);
    }

    protected boolean containsSingleton(String beanName) {
        return singletonObjects.containsKey(beanName);
    }

    protected String[] getSingletonNames() {
        return singletonObjects.keySet().toArray(new String[0]);
    }

    protected int getSingletonCount() {
        return singletonObjects.size();
    }
}
