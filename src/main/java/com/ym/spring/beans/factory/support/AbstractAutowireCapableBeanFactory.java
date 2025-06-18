package com.ym.spring.beans.factory.support;

import com.ym.spring.beans.BeansException;
import com.ym.spring.beans.PropertyValue;
import com.ym.spring.beans.PropertyValues;
import com.ym.spring.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

/**
 * 抽象自动装配Bean工厂
 *
 * <p>负责处理bean的自动装配(createBean), 包括
 * 创建, 属性填充(自动装配), 初始化以及执行各种回调的全过程
 *
 * <p>通过继承AbstractBeanFactory拥有了单例bean管理能力(getBean)
 *
 * <p>定义为抽象类的原因:
 * 只专注于如何创建bean, 而将从哪里获取bean的定义(BeanDefinition)这个任务交给它的子类去实现
 *
 * @author YinMing
 * @since 2025/5/7 08:53
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    /**
     * 创建Bean实例, 填充属性, 初始化Bean, 注册单例Bean
     * @param beanName Bean名称
     * @param beanDefinition Bean定义
     * @param args 构造参数
     * @return Bean实例
     * @throws BeansException 创建Bean失败时抛出异常
     */
    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException {
        Object bean;
        try {
            // 1. 创建Bean实例
            bean = createBeanInstance(beanDefinition, beanName, args);

            // 2. 给Bean填充属性
            applyPropertyValues(beanName, bean, beanDefinition);

            // 3. 初始化Bean
            initializeBean(beanName, bean, beanDefinition);
        } catch (Exception e) {
            throw new BeansException("Instantiation of bean failed", e);
        }

        // 注册单例Bean
        if (beanDefinition.isSingleton()) {
            registerSingleton(beanName, bean);
        }

        return bean;
    }

    /**
     * (用构造函数)创建Bean实例
     * @param beanDefinition Bean定义
     * @param beanName Bean名称
     * @param args 构造参数
     * @return Bean实例
     */
    protected Object createBeanInstance(BeanDefinition beanDefinition, String beanName, Object[] args) {
        Constructor<?> constructorToUse = null;
        Class<?> beanClass = beanDefinition.getBeanClass();
        Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();

        // 如果有参数, 找匹配的构造函数
        if (args != null && args.length > 0) {
            for (Constructor<?> constructor : declaredConstructors) {
                if (constructor.getParameterTypes().length == args.length) {
                    constructorToUse = constructor;
                    break;
                }
            }

            // 没找到匹配的构造函数, 抛出异常
            if (constructorToUse == null) {
                throw new BeansException("No constructor found for class [" + beanClass.getName() + "]", new NoSuchMethodException());
            }

            // 用匹配的构造函数创建Bean
            try {
                return constructorToUse.newInstance(args);
            } catch (Exception e) {
                throw new BeansException("Fail to instantiate '" + beanName + "'", e);
            }
        } else {
            // 如果没有参数, 用默认构造函数
            try {
                return beanClass.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                throw new BeansException("Fail to instantiate '" + beanName + "' with no argument", e);
            }
        }
    }

    /**
     * 给Bean填充属性
     * @param beanName Bean名称
     * @param bean Bean实例
     * @param beanDefinition Bean定义
     */
    protected void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) {
        try {
            PropertyValues propertyValues = beanDefinition.getPropertyValues();

            for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
                String name = propertyValue.getName();
                Object value = propertyValue.getValue();

                // 优先用转换值
                Object valueToUse = propertyValue.getConvertedValue();
                if (valueToUse == null){
                    valueToUse = value;
                }

                // 给Bean填充属性
                Field field = bean.getClass().getDeclaredField(name);
                field.setAccessible(true);
                field.set(bean, valueToUse);
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new BeansException("Error setting property values for bean '" + beanName + "'", e);
        }
    }

    /**
     * 初始化Bean
     * @param beanName Bean名称
     * @param bean Bean实例
     * @param beanDefinition Bean定义
     */
    private void initializeBean(String beanName, Object bean, BeanDefinition beanDefinition) {
        invokeInitMethods(beanName, bean, beanDefinition);
    }

    private void invokeInitMethods(String beanName, Object bean, BeanDefinition beanDefinition) {
        String initMethodName = beanDefinition.getInitMethodName();
        if (initMethodName != null && !initMethodName.isEmpty()) {
            try {
                bean.getClass().getMethod(initMethodName).invoke(bean);
            } catch (Exception e) {
                throw new BeansException("Invocation of init method of bean[" + beanName + "] failed", e);
            }
        }
    }
}
