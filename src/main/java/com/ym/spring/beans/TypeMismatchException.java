package com.ym.spring.beans;

/**
 * @author YinMing
 * @since 2025/6/19 15:33
 */
public class TypeMismatchException extends BeansException {

    private final Class<?> requiredType;

    private final Object value;

    public TypeMismatchException(Object value, Class<?> requiredType) {
      super("Cannot convert from [" + value + "] to [" + requiredType.getName() + "]");
      this.value = value;
      this.requiredType = requiredType;
    }

    public TypeMismatchException(Object value, Class<?> requiredType, Throwable cause) {
      super("Cannot convert from [" + value + "] to [" + requiredType.getName() + "]", cause);
      this.value = value;
      this.requiredType = requiredType;
    }

    public Class<?> getRequiredType() {
      return requiredType;
    }

    public Object getValue() {
      return value;
    }
}
