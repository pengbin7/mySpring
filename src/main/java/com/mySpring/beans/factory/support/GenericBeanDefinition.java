package com.mySpring.beans.factory.support;

import java.util.ArrayList;
import java.util.List;

import com.mySpring.beans.BeanDefinition;
import com.mySpring.beans.PropertyValue;

public class GenericBeanDefinition implements BeanDefinition {

	private String id;
	
	private String beanClassName;
	
	private boolean singleton = true;
	
	private boolean prototype = false;
	
	private String scope = SCOPE_DEFAULT;
	
	List<PropertyValue> propertyValues = new ArrayList<PropertyValue>();
	
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

	public boolean isSingleton() {
		return this.singleton;
	}

	public boolean isPrototype() {
		return this.prototype;
	}

	public String getScope() {
		return this.scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
		this.singleton = SCOPE_SINGLETON.equals(scope)|| SCOPE_DEFAULT.equals(scope);
		this.prototype = SCOPE_PROTOTYPE.equals(scope);
	}

	public List<PropertyValue>  getPropertyValues(){
		return this.propertyValues;
	}
}
