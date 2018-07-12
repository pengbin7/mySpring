package com.mySpring.test.v1;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import com.mySpring.beans.BeanDefinition;
import com.mySpring.beans.factory.BeanCreationException;
import com.mySpring.beans.factory.BeanDefinitionStoreException;
import com.mySpring.beans.factory.BeanFactory;
import com.mySpring.beans.factory.support.DefaultBeanFactory;
import com.mySpring.service.v1.PetStoreService;

public class BeanFactoryTest {

	/**
	 * TDD
	 * 
	 * 1.创建bean工厂类，传入xml参数
	 * 2.根据映射名得到bean的类型
	 * 3.校验得到的bean的类型是否与xml参数一致
	 * 4.根据映射名得到bea
	 * 5.校验bean是否为空
	 */
	@Test
	public void testGetBean() {
		BeanFactory factory = new DefaultBeanFactory("petstore-v1.xml");
		BeanDefinition bd = factory.getBeanDefinition("petStore");
		assertEquals("com.mySpring.service.v1.PetStoreService", bd.getBeanClassName());
		PetStoreService petStore = (PetStoreService)factory.getBean("petStore");
		assertNotNull(petStore);
	}

	/**
	 * 测试bean创建异常
	 */
	@Test
	public void testInvalidBean(){
		BeanFactory factory = new DefaultBeanFactory("petstore-v1.xml");
		try {
			factory.getBean("invalidBean");
		} catch (BeanCreationException e) {
			return;
		}
		Assert.fail("expect BeanCreationException");
	}
	
	/**
	 * 测试xml读取异常
	 */
	@Test
	public void testInvalidXml(){
		try {
		  new DefaultBeanFactory("xxx.xml");
		} catch (BeanDefinitionStoreException e) {
			return;
		}
		Assert.fail("ecpect BeanDefinitionStoreException");
	}
}
