package com.mySpring.beans.factory.config;

public interface SingletonBeanRegistry {

	void registerSingleton(String beanName,Object singletonObject);
	
	Object getSigleton(String beanName);
}
