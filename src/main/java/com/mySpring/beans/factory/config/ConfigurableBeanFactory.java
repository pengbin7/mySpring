package com.mySpring.beans.factory.config;

import com.mySpring.beans.factory.BeanFactory;

public interface ConfigurableBeanFactory  extends BeanFactory{

	void setBeanClassLoader(ClassLoader beanClassLoader);
	
	ClassLoader getBeanClassLoader();
}
