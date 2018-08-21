package com.mySpring.beans.factory.support;

import com.mySpring.beans.BeanDefinition;

public interface BeanDefinitionRegistry {

	BeanDefinition getBeanDefinition(String beanID);
	
	void registerBeanDefinition(String beanID,BeanDefinition bd);
}
