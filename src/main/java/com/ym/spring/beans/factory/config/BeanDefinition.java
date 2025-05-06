package com.ym.spring.beans.factory.config;

import com.ym.spring.beans.PropertyValues;

/**
 * @author YinMing
 * @since 2025/5/6 10:11
 */
public class BeanDefinition {

    private Class<?> beanClass;

    private PropertyValues propertyValues;

    private String initMethodName;

    private String destroyMethodname;

    private boolean singleton = true;

    private boolean prototype = false;

    /**
     * 作用域
     */
    public static final String SCOPE_SINGLETON = "singleton";

    public static final String SCOPE_PROTOTYPE = "prototype";
}
