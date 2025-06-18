package com.ym.spring.beans.factory.support;

import com.ym.spring.beans.BeansException;
import com.ym.spring.beans.factory.config.BeanDefinition;

import java.util.HashMap;
import java.util.Map;

/**
 * 默认可列表Bean工厂
 *
 * <p>是BeanFactory接口最完整的实现
 *
 * <p>通过继承AbstractAutowireCapableBeanFactory获得了getBean和createBean的能力
 *
 * <p>Listable这个词的意义在于可枚举或可列表化. 它指的是BeanFactory拥有的一种遍历和查询其内部注册的所有bean定义的能力.
 * 这个特性非常关键, 它意味着容器不仅仅是一个被动的"bean仓库", 只能在被调用时根据名字或类型返回一个bean.
 * 相反, 它变成了一个可审查的, 可查询的容器。
 *
 * @author YinMing
 * @since 2025/5/7 10:47
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry {

    private final Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName, beanDefinition);
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanName) throws BeansException {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if (beanDefinition == null) {
            throw new BeansException("No bean named '" + beanName + "' is defined");
        }
        return beanDefinition;
    }

    @Override
    public boolean containsBeanDefinition(String beanName) {
        return beanDefinitionMap.containsKey(beanName);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return beanDefinitionMap.keySet().toArray(new String[0]);
    }

    public String[] getBeanNamesForType(Class<?> type) {
        return beanDefinitionMap.entrySet().stream()
                .filter(entry -> type.isAssignableFrom(entry.getValue().getBeanClass()))
                .map(Map.Entry::getKey)
                .toArray(String[]::new);
    }
}
