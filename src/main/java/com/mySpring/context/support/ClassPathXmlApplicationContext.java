package com.mySpring.context.support;
import com.mySpring.core.io.ClassPathResource;
import com.mySpring.core.io.Resource;

public class ClassPathXmlApplicationContext extends AbstractApplicationContext {

	public ClassPathXmlApplicationContext(String configFile) {
		super(configFile);
	}

	@Override
	protected Resource getResourceByPath(String path) {
		return new ClassPathResource(path,this.getBeanClassLoader());
	}

	
}
