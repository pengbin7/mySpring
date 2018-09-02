package com.mySpring.test.v2;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.mySpring.beans.BeanDefinition;
import com.mySpring.beans.PropertyValue;
import com.mySpring.beans.factory.config.RuntimeBeanReference;
import com.mySpring.beans.factory.support.DefaultBeanFactory;
import com.mySpring.beans.factory.xml.XmlBeanDefinitionReader;
import com.mySpring.core.io.ClassPathResource;

public class BeanDefinitionTestV2 {

	@Test
	public void testGetBeanDefinition(){
		DefaultBeanFactory factory = new DefaultBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
		reader.loadBeanDefinitions(new ClassPathResource("petstore-v2.xml"));
		BeanDefinition bd = factory.getBeanDefinition("petStore");
		
		List<PropertyValue> pvs = bd.getPropertyValues();
		
		Assert.assertTrue(pvs.size()==2);
		
		{
			PropertyValue pv = this.getPropertyValue("acountDao",pvs);
			Assert.assertNotNull(pv);
			Assert.assertTrue(pv.getValue() instanceof RuntimeBeanReference);
		}
		
		{
			PropertyValue pv = this.getPropertyValue("itemDao",pvs);
			Assert.assertNotNull(pv);
			Assert.assertTrue(pv.getValue() instanceof RuntimeBeanReference);
		}
	}

	
	private PropertyValue getPropertyValue(String beanName,List<PropertyValue> pvs){
		for (PropertyValue pv : pvs) {
			if(pv.getName().equals(beanName)){
				return pv;
			}
		}
		return null;
	}

}
