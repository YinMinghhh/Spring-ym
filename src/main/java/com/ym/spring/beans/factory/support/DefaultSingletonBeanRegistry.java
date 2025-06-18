package com.ym.spring.beans.factory.support;

import com.ym.spring.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * 单例Bean注册和管理类
 *
 * <p>管理和维护所有单例bean实例的缓存
 *
 * <p>为了保证单例bean的唯一性和共享性, 就必须有一个地方能够存储并持有这个创建好的唯一实例.
 * 这个"存储并持有"的动作, 就是"注册"(Register).
 *
 * <p>protocol作用域不需要注册. request 和 session 需要注册, 但它们不使用这样的全局注册表,
 * 分别使用当前的HTTP请求对象HttpServletRequest本身和当前的HTTP会话对象HttpSession本身.
 *
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
