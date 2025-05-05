package com.ym.spring.beans;

/**
 * @author yinMing
 * @since 2025/5/5 21:44
 */
public class BeansException extends RuntimeException{

    public BeansException() {
        super();
    }

    public BeansException(String message) {
        super(message);
    }

    public BeansException(String message, Throwable cause) {
        super(cause);
    }
}
