package com.ym.spring;

import com.ym.spring.bean.UserDaoImpl;
import com.ym.spring.bean.UserService;
import com.ym.spring.beans.PropertyValue;
import com.ym.spring.beans.PropertyValues;
import com.ym.spring.beans.factory.config.BeanDefinition;
import com.ym.spring.beans.factory.support.DefaultListableBeanFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author YinMing
 * @since 2025/5/8 09:10
 */
class ApiTest {
    /**
     * 测试Bean工厂初始化, Bean定义注册, 依赖关系配置, Bean的获取和使用
     */
    @Test
    void testBeanFactory() {
        // 1. 初始化BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2. 注册 userDao
        BeanDefinition userDaoBeanDefinition = new BeanDefinition(UserDaoImpl.class);
        beanFactory.registerBeanDefinition("userDao", userDaoBeanDefinition);

        // 3. 注册 userService
        BeanDefinition userServiceBeanDefinition = new BeanDefinition(UserService.class);
        userServiceBeanDefinition.setInitMethodName("init");

        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("name", "赵钱孙"));
        propertyValues.addPropertyValue(new PropertyValue("userDao", beanFactory.getBean("userDao")));
        userServiceBeanDefinition.setPropertyValues(propertyValues);

        beanFactory.registerBeanDefinition("userService", userServiceBeanDefinition);

        // 4. 获取Bean
        UserService userService = (UserService) beanFactory.getBean("userService");

        // 5. 验证结果
        String result = userService.queryUserInfo();
        System.out.println("测试结果: " + result);

        Assertions.assertEquals("上海市", result, "测试失败");
    }

    /**
     * 测试PropertyValues的功能
     */
    @Test
    void testPropertyValues() {

        PropertyValues propertyValues = new PropertyValues();

        // 添加属性
        propertyValues.addPropertyValue(new PropertyValue("name", "周吴郑"));
        propertyValues.addPropertyValue(new PropertyValue("age", 25));

        // 测试获取属性
        Assertions.assertTrue(propertyValues.contains("name"));
        Assertions.assertTrue(propertyValues.contains("age"));
        Assertions.assertFalse(propertyValues.contains("gender"));

        // 测试属性数量
        Assertions.assertEquals(2, propertyValues.size());

        // 测试获取属性的值
        Assertions.assertEquals("周吴郑", propertyValues.getPropertyValue("name").orElseThrow().getValue());
        Assertions.assertEquals(25, propertyValues.getPropertyValue("age").orElseThrow().getValue());

        // 测试属性值替换
        propertyValues.addPropertyValue(new PropertyValue("name", "赵钱孙"));
        Assertions.assertEquals("赵钱孙", propertyValues.getPropertyValue("name").orElseThrow().getValue());

        // 测试属性值转换
        PropertyValue nameProperty = propertyValues.getPropertyValue("name").orElseThrow();
        nameProperty.setConvertedValue("冯陈褚");
        Assertions.assertEquals("冯陈褚", propertyValues.getPropertyValue("name").orElseThrow().getConvertedValue());
        Assertions.assertEquals("赵钱孙", propertyValues.getPropertyValue("name").orElseThrow().getValue());
    }
}
