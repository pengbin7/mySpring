package com.mySpring.context.support;

import com.mySpring.beans.factory.BeanFactory;
import com.mySpring.beans.factory.config.RuntimeBeanReference;
import com.mySpring.beans.factory.config.TypedStringValue;
import com.mySpring.beans.factory.support.DefaultBeanFactory;

public class BeanDefinitionValueResolver {

    private final DefaultBeanFactory beanFactory;
    public BeanDefinitionValueResolver(DefaultBeanFactory factory){
        this.beanFactory = factory;
    }

    public Object resolverValueIfNecessary(Object value) {

        if(value instanceof  RuntimeBeanReference){
            RuntimeBeanReference ref = (RuntimeBeanReference)value;
            String refName = ref.getBeanName();
            Object bean = this.beanFactory.getBean(refName);
            return bean;
        }else if(value instanceof TypedStringValue){
            return ((TypedStringValue)value).getValue();
        }else{
            //TODO
            throw new RuntimeException("the value "+value +"has not implemented");
        }
    }
}
