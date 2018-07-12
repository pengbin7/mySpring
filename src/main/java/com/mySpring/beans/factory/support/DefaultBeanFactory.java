package com.mySpring.beans.factory.support;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.litespring.util.ClassUtils;

import com.mySpring.beans.BeanDefinition;
import com.mySpring.beans.factory.BeanCreationException;
import com.mySpring.beans.factory.BeanDefinitionStoreException;
import com.mySpring.beans.factory.BeanFactory;

public class DefaultBeanFactory implements BeanFactory{

	public static final String ID_ATTRIBUTE = "id";
	public static final String CLASS_ATTRIBUTE = "class";
	private final Map<String,BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>(64);

	public DefaultBeanFactory(){}
	
	public DefaultBeanFactory(String configFile) {
		loadBeanDefinition(configFile);
	}
	
	public void loadBeanDefinition(String configFile){
		InputStream is = null;
	    try {
	    	ClassLoader cl = ClassUtils.getDefaultClassLoader();
			is = cl.getResourceAsStream(configFile);
			SAXReader reader = new SAXReader();
			Document doc = reader.read(is);
			Element root =doc.getRootElement();
			Iterator<Element> iter = root.elementIterator();
			while(iter.hasNext()){
				Element ele = (Element)iter.next();
				String id = ele.attributeValue(ID_ATTRIBUTE);
				String beanClassName = ele.attributeValue(CLASS_ATTRIBUTE);
				BeanDefinition bd = new GenericBeanDefinition(id,beanClassName);
				this.beanDefinitionMap.put(id,bd);
			}
		} catch (DocumentException e) {
			throw new BeanDefinitionStoreException("IOException parsing XML document");
		}finally{
			if(is != null){
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public BeanDefinition getBeanDefinition(String beanID) {
		return this.beanDefinitionMap.get(beanID);
	}

	public Object getBean(String beanID) {
		BeanDefinition bd = this.getBeanDefinition(beanID);
	    if(bd == null){
	    	throw new BeanCreationException("Bean Definition does not exist");
	    }
		ClassLoader cl = ClassUtils.getDefaultClassLoader();
		String beanName = bd.getBeanClassName();
		try {
			Class<?> clz = cl.loadClass(beanName);
			//默认存在一个非空构造，可以通过反射获得对象
			return clz.newInstance();
		} catch (Exception e) {
			throw new BeanCreationException("Bean Definition does not exist");
		}
	}

}
