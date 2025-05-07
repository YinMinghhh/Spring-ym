package com.ym.spring.beans;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Bean属性集合
 * @author yinMing
 * @since 2025/5/5 22:10
 */
public class PropertyValues {

    private final List<PropertyValue> propertyValueList = new ArrayList<>();

    /**
     * 添加属性
     * <p>如果有同名属性就替换
     * @param propertyValue 要添加的属性值
     * @throws IllegalArgumentException 如果propertyValue为null
     */
    public void addPropertyValue(PropertyValue propertyValue) {
        if (propertyValue == null) {
            throw new IllegalArgumentException("propertyValue is null");
        }

        this.propertyValueList.removeIf(existing -> existing.getName().equals(propertyValue.getName()));
        this.propertyValueList.add(propertyValue);
    }

    /**
     * 获取所有属性
     * @return 属性值数组, 不会为null
     */
    public PropertyValue[] getPropertyValues() {
        return propertyValueList.toArray(new PropertyValue[0]);
    }

    public Optional<PropertyValue> getPropertyValue(String propertyName) {
        for (PropertyValue propertyValue : propertyValueList) {
            if (propertyValue.getName().equals(propertyName)) {
                return Optional.of(propertyValue);
            }
        }
        return Optional.empty();
    }

    public boolean contains(String propertyName) {
        return getPropertyValue(propertyName).isPresent();
    }

    public int size() {
        return propertyValueList.size();
    }

    public boolean isEmpty() {
        return propertyValueList.isEmpty();
    }

    public List<PropertyValue> getPropertyValueList() {
        return Collections.unmodifiableList(propertyValueList);
    }
}
