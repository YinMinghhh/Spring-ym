package com.ym.spring.beans.factory.support;

import com.ym.spring.beans.BeansException;
import com.ym.spring.beans.factory.config.BeanDefinition;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

import java.lang.reflect.Constructor;

/**
 * BeanFactories中使用的默认的对象实例化策略
 *
 * <p>用cglib策略实现动态代理, 需要代理增强时可以使用
 *
 * @author YinMing
 * @since 2025/6/18 16:13
 */
public class CglibSubclassInstantiationStrategy extends SimpleInstantiationStrategy {

    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor<?> ctor, Object[] args) {
        try {
            Enhancer enhancer = new Enhancer();

            enhancer.setSuperclass(beanDefinition.getBeanClass());

            // 无增强逻辑
            enhancer.setCallback(NoOp.INSTANCE);

            if (ctor == null) {
                return enhancer.create();
            }

            args = args == null ? new Object[0] : args;

            if (args.length != ctor.getParameterCount()) {
                throw new BeansException("Parameter count does not match: " + args.length + ", but expected: " + ctor.getParameterCount());
            }

            return enhancer.create(ctor.getParameterTypes(), args);
        } catch (Exception e) {
            throw new BeansException("Fail to instantiate bean [" + beanName + "] using cglib", e);
        }
    }
}
