package com.mySpring.context.support;

import org.litespring.util.ClassUtils;

import com.mySpring.beans.factory.support.DefaultBeanFactory;
import com.mySpring.beans.factory.xml.XmlBeanDefinitionReader;
import com.mySpring.context.ApplicationContext;
import com.mySpring.core.io.Resource;

public abstract class AbstractApplicationContext implements ApplicationContext {

    private DefaultBeanFactory factory = null;
    private ClassLoader beanClassLoader;
	
	public AbstractApplicationContext(String configFile){
		factory = new DefaultBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
		Resource resource = this.getResourceByPath(configFile);
		reader.loadBeanDefinitions(resource);
		factory.setBeanClassLoader(this.beanClassLoader);
	}
	
	public Object getBean(String beanID){
		return factory.getBean(beanID);
	}
	
	protected abstract Resource getResourceByPath(String path);

	public void setBeanClassLoader(ClassLoader beanClassLoader) {
		this.beanClassLoader = beanClassLoader;
		
	}

	public ClassLoader getBeanClassLoader() {
		return (this.beanClassLoader !=null ? this.beanClassLoader:ClassUtils.getDefaultClassLoader());
	}
}
