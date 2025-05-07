package com.ym.spring.beans;

/**
 * Bean属性值
 * @author yinMing
 * @since 2025/5/5 21:46
 */
public class PropertyValue {

    /**
     * Bean名称
     */
    private final String name;

    /**
     * 原始值
     */
    private final Object value;

    /**
     * 转换后的值
     */
    private Object convertedValue;

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }

    public Object getConvertedValue() {
        return convertedValue;
    }

    public void setConvertedValue(Object convertedValue) {
        this.convertedValue = convertedValue;
    }
}
