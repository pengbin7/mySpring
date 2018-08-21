package com.mySpring.beans.factory.support;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.litespring.util.Assert;

import com.mySpring.beans.factory.config.SingletonBeanRegistry;

public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

	private final Map<String,Object> singletonObjects = new ConcurrentHashMap<String,Object>(64);
	
	public void registerSingleton(String beanName, Object singletonObject) {
		Assert.notNull(beanName, "'beanName' must not be null");
		
		Object oldObject = this.singletonObjects.get(beanName);
		if(oldObject != null){
			throw new IllegalStateException("Could not register object ["+singletonObject+
					"] under bean name '"+beanName+"':there is already object ["+oldObject+"]");
		}
		this.singletonObjects.put(beanName, singletonObject);

	}

	public Object getSigleton(String beanName) {
		return this.singletonObjects.get(beanName);
	}

}
