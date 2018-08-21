package com.mySpring.test.v1;

import org.junit.Assert;
import org.junit.Test;

import com.mySpring.context.ApplicationContext;
import com.mySpring.context.support.ClassPathXmlApplicationContext;
import com.mySpring.context.support.FileSystemXmlApplicationContext;
import com.mySpring.service.v1.PetStoreService;

public class ApplicationContextTest {

	@Test
	public void testGetBean(){
		ApplicationContext context = new ClassPathXmlApplicationContext("petstore-v1.xml");
		PetStoreService petStore = (PetStoreService)context.getBean("petStore");
		Assert.assertNotNull(petStore);
	}
	
	@Test
	public void testGetBeanFromFileSystemContext(){
		
		//这里仍然是hardcode了一个本地路径，这是不好的实践，如何处理？
		ApplicationContext ctx = new FileSystemXmlApplicationContext("H:/Spring/mySpring/src/test/resources/petstore-v1.xml");
		PetStoreService petStoreService = (PetStoreService)ctx.getBean("petStore");
		Assert.assertNotNull(petStoreService);
	}
}
