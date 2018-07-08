package com.mySpring.beans.factory.support;

import com.mySpring.beans.BeanDefinition;

public class GenericBeanDefinition implements BeanDefinition {

	private String id;
	
	private String beanClassName;
	
	public GenericBeanDefinition(){
		
	}
	
	public GenericBeanDefinition(String id, String beanClassName) {
		this.id = id;
		this.beanClassName = beanClassName;
	}

	public Object getBeanClassName() {
		return null;
	}

}
