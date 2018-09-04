package com.mySpring.test.v2;

import static org.junit.Assert.*;

import com.mySpring.dao.v2.AcountDao;
import org.junit.Assert;
import org.junit.Test;

import com.mySpring.context.ApplicationContext;
import com.mySpring.context.support.ClassPathXmlApplicationContext;
import com.mySpring.service.v2.PetStoreService;

public class ApplicationContextTestV2 {

	//测试applicationContext实现依赖注入
	@Test
	public void testGetBean(){
		ApplicationContext context = new ClassPathXmlApplicationContext("petstore-v2.xml");
		PetStoreService petStore = (PetStoreService)context.getBean("petStore");
		Assert.assertNotNull(petStore.getAcountDao());
		assertNotNull(petStore.getItemDao());

		assertTrue(petStore.getAcountDao() instanceof AcountDao);

		assertEquals("peng",petStore.getOwner());

		assertEquals(1,petStore.getVersion());
	}
	

}
