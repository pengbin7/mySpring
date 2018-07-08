package com.mySpring.beans.factory.support;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.litespring.util.ClassUtils;

import com.mySpring.beans.BeanDefinition;
import com.mySpring.beans.factory.BeanFactory;
import com.mySpring.service.v1.PetStoreService;

public class DefaultBeanFactory implements BeanFactory{

	public static final String ID_ATTRIBUTE = "id";
	private static final String CLASS_ATTRIBUTE = "class";
	public  HashMap beanDefinitionMap;

	public DefaultBeanFactory(){
		
	}
	
	public DefaultBeanFactory(String configFile) {
		InputStream is = null;
	    try {
	    	ClassLoader cl = ClassUtils.getDefaultClassLoader();
			is = cl.getResourceAsStream(configFile);
			SAXReader reader = new SAXReader();
			Document doc = reader.read(is);
			Element root =doc.getRootElement();
			Iterator<Element> iter = root.elementIterator();
			while(iter.hasNext()){
			String id = root.attributeValue(ID_ATTRIBUTE);
			String beanClassName = root.attributeValue(CLASS_ATTRIBUTE);
			BeanDefinition bd = new GenericBeanDefinition(id,beanClassName);
			this.beanDefinitionMap.put(id,bd);
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

	public BeanDefinition getBeanDefinition(String string) {
		return null;
	}

	public PetStoreService getBean(String string) {
		// TODO Auto-generated method stub
		return null;
	}

}
