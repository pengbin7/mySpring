package com.mySpring.test.v1;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.mySpring.beans.BeanDefinition;
import com.mySpring.beans.factory.BeanCreationException;
import com.mySpring.beans.factory.BeanDefinitionStoreException;
import com.mySpring.beans.factory.BeanFactory;
import com.mySpring.beans.factory.support.DefaultBeanFactory;
import com.mySpring.beans.factory.xml.XmlBeanDefinitionReader;
import com.mySpring.service.v1.PetStoreService;

public class BeanFactoryTest {

	DefaultBeanFactory factory = null;
	
	XmlBeanDefinitionReader reader = null;
	
	/**
	 * 重构测试用例，
	 * 在每次运行测试用例时都运行配置代码
	 * 重置factory/reader，每次都是全新的对象
	 * 各个测试用例之间互不影响
	 * 
	 */
	@Before
	public void setUp(){
		factory = new DefaultBeanFactory();
		reader = new XmlBeanDefinitionReader(factory);
	}
	
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
		reader.loadBeanDefinition("petstore-v1.xml");
		//BeanFactory factory = new DefaultBeanFactory("petstore-v1.xml");
		BeanDefinition bd = factory.getBeanDefinition("petStore");
		
		assertTrue(bd.isSingleton());
		assertFalse(bd.isPrototype());
		assertEquals(BeanDefinition.SCOPE_DEFAULT,bd.getScope());
		
		assertEquals("com.mySpring.service.v1.PetStoreService", bd.getBeanClassName());
		PetStoreService petStore = (PetStoreService)factory.getBean("petStore");
		assertNotNull(petStore);
		
		PetStoreService petStore1 = (PetStoreService)factory.getBean("petStore");
		assertTrue(petStore.equals(petStore1));
	}

	/**
	 * 测试bean创建异常
	 */
	@Test
	public void testInvalidBean(){
		reader.loadBeanDefinition("petstore-v1.xml");
		//BeanFactory factory = new DefaultBeanFactory("petstore-v1.xml");
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
			reader.loadBeanDefinition("xxx.xml");
		  //new DefaultBeanFactory("xxx.xml");
		} catch (BeanDefinitionStoreException e) {
			return;
		}
		Assert.fail("ecpect BeanDefinitionStoreException");
	}
}
