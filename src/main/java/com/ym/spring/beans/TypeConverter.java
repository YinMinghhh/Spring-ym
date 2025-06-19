package com.ym.spring.beans;

/**
 * @author YinMing
 * @since 2025/6/19 15:29
 */
public interface TypeConverter {

    <T> T convertIfNecessary(Object value, Class<T> requiredType) throws TypeMismatchException;
}
