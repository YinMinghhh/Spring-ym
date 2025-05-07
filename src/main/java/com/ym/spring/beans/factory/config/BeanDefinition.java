package com.ym.spring.beans.factory.config;

import com.ym.spring.beans.PropertyValues;

import java.util.HashMap;
import java.util.Map;

/**
 * @author YinMing
 * @since 2025/5/6 10:11
 */
public class BeanDefinition {

    private Class<?> beanClass;

    private PropertyValues propertyValues;

    private String initMethodName;

    private String destroyMethodName;

    private boolean singleton = true;

    private boolean prototype = false;

    private String scope = SCOPE_SINGLETON;

    private boolean scopeProxy = false;

    private final Map<String, Object> attributes = new HashMap<>();

    /**
     * 单例模式
     */
    public static final String SCOPE_SINGLETON = "singleton";

    /**
     * 原型模式
     */
    public static final String SCOPE_PROTOTYPE = "prototype";

    public BeanDefinition(Class<?> beanClass) {
        this.beanClass = beanClass;
        this.propertyValues = new PropertyValues();
    }

    public BeanDefinition(Class<?> beanClass, PropertyValues propertyValues) {
        this.beanClass = beanClass;
        this.propertyValues = propertyValues != null ? propertyValues : new PropertyValues();
    }

    public Class<?> getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class<?> beanClass) {
        this.beanClass = beanClass;
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }

    public String getInitMethodName() {
        return initMethodName;
    }

    public void setInitMethodName(String initMethodName) {
        this.initMethodName = initMethodName;
    }

    public String getDestroyMethodName() {
        return destroyMethodName;
    }

    public void setDestroyMethodName(String destroyMethodName) {
        this.destroyMethodName = destroyMethodName;
    }

    public boolean isSingleton() {
        return singleton;
    }

    public void setSingleton(boolean singleton) {
        this.singleton = singleton;
    }

    public boolean isPrototype() {
        return prototype;
    }

    public void setPrototype(boolean prototype) {
        this.prototype = prototype;
    }

    public void setScope(String scope) {
        this.scope = scope;
        this.singleton = SCOPE_SINGLETON.equals(scope);
        this.prototype = SCOPE_PROTOTYPE.equals(scope);
    }

    public String getScope() {
        return scope;
    }

    public boolean isScopeProxy() {
        return scopeProxy;
    }

    public void setScopeProxy(boolean scopeProxy) {
        this.scopeProxy = scopeProxy;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttribute(String name, Object value) {
        attributes.put(name, value);
    }

    public Object getAttribute(String name) {
        return attributes.get(name);
    }

    public boolean hasAttribute(String name) {
        return attributes.containsKey(name);
    }
}
