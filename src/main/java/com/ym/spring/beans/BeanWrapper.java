package com.ym.spring.beans;

/**
 * @author YinMing
 * @since 2025/6/19 15:18
 */
public class BeanWrapper {

    private final Object wrappedInstance;
    private Class<?> wrappedClass;

    public BeanWrapper(Object wrappedInstance) {
        this.wrappedInstance = wrappedInstance;
        this.wrappedClass = wrappedInstance.getClass();
    }

    public Object getWrappedInstance() {
        return wrappedInstance;
    }

    public Class<?> getWrappedClass() {
        return wrappedClass;
    }
}
