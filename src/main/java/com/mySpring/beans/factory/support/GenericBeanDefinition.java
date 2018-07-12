package com.mySpring.beans.factory.support;

import com.mySpring.beans.BeanDefinition;

public class GenericBeanDefinition implements BeanDefinition {

	private String id;
	
	private String beanClassName;
	
	public GenericBeanDefinition(){
		
	}
	
	public GenericBeanDefinition(String id, String beanClassName) {
		this.setId(id);
		this.setBeanClassName(beanClassName);
	}

	public String getBeanClassName() {
		return this.beanClassName;
	}

	public void setBeanClassName(String beanClassName) {
		this.beanClassName = beanClassName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
