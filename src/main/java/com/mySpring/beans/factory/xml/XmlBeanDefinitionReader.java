package com.mySpring.beans.factory.xml;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import com.mySpring.beans.PropertyValue;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import com.mySpring.util.ClassUtils;

import com.mySpring.beans.BeanDefinition;
import com.mySpring.beans.factory.BeanDefinitionStoreException;
import com.mySpring.beans.factory.config.RuntimeBeanReference;
import com.mySpring.beans.factory.config.TypedStringValue;
import com.mySpring.beans.factory.support.BeanDefinitionRegistry;
import com.mySpring.beans.factory.support.GenericBeanDefinition;
import com.mySpring.core.io.Resource;
import com.mySpring.util.StringUtils;


public class XmlBeanDefinitionReader {

	public static final String ID_ATTRIBUTE = "id";

	public static final String CLASS_ATTRIBUTE = "class";

	public static final String SCOPE_ATTRIBUTE = "scope";

	public static final String PROPERTY_VALUE = "property";

	public static final String REF_ATTRIBUTE = "ref";

	public static final String VALUE_ATTRIBUTE = "value";

	public static final String NAME_ATTRIBUTE = "name";

	BeanDefinitionRegistry registry;

	protected final Log logger = LogFactory.getLog(getClass());

	public XmlBeanDefinitionReader(BeanDefinitionRegistry registry){
	   this.registry = registry;
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
				if(ele.attributeValue(SCOPE_ATTRIBUTE)!= null){
					bd.setScope(ele.attributeValue(SCOPE_ATTRIBUTE));
				}else{
					bd.setScope("");
				}
				this.registry.registerBeanDefinition(id,bd);
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

	public void loadBeanDefinitions(Resource resource){
		InputStream is = null;
	    try {
	    	ClassLoader cl = ClassUtils.getDefaultClassLoader();
			is = resource.getInputStream();
			SAXReader reader = new SAXReader();
			Document doc = reader.read(is);
			Element root =doc.getRootElement();
			Iterator<Element> iter = root.elementIterator();
			while(iter.hasNext()){
				Element ele = (Element)iter.next();
				String id = ele.attributeValue(ID_ATTRIBUTE);
				String beanClassName = ele.attributeValue(CLASS_ATTRIBUTE);
				BeanDefinition bd = new GenericBeanDefinition(id,beanClassName);
				parsePropertyElement(ele,bd);
				this.registry.registerBeanDefinition(id,bd);
			}
		} catch (Exception e) {
			throw new BeanDefinitionStoreException("IOException parsing XML document"+ resource.getDescription(),e);
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

	private void parsePropertyElement(Element ele, BeanDefinition bd) {
		Iterator iter = ele.elementIterator(PROPERTY_VALUE);
		while(iter.hasNext()){
			Element propElem = (Element)iter.next();
			String propertyName = propElem.attributeValue(NAME_ATTRIBUTE);
			if(!StringUtils.hasLength(propertyName)){
				logger.fatal("Tag 'property' must have a 'name' attribute");
				return;
			}
			Object val = parsePropertyValue(propElem,bd,propertyName);
			PropertyValue pv = new PropertyValue(propertyName,val);
			bd.getPropertyValues().add(pv);
		}
	}


	public Object parsePropertyValue(Element ele, BeanDefinition bd, String propertyName) {
		String elementName = (propertyName == null) ?
				"<property> element for property '" + propertyName + "'" :
				"<constructor-arg> element";
		boolean hasRefAttribute = (ele.attribute(REF_ATTRIBUTE) != null);

		boolean hasValueAttribute = (ele.attribute(VALUE_ATTRIBUTE) != null);

		if (hasRefAttribute) {
			String refName = ele.attributeValue(REF_ATTRIBUTE);
			if (!StringUtils.hasLength(refName)) {
				logger.error(elementName + "contains empty 'ref' attribute");
			}
			RuntimeBeanReference ref = new RuntimeBeanReference(refName);
			return ref;
		} else if (hasValueAttribute) {
			TypedStringValue valueHolder = new TypedStringValue(ele.attributeValue(VALUE_ATTRIBUTE));
			return valueHolder;
		} else {
			//TODO
			throw new RuntimeException(elementName + "");
		}

	}
}
