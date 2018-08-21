package com.mySpring.test.v1;

import java.io.InputStream;

import org.junit.Assert;
import org.junit.Test;

import com.mySpring.core.io.ClassPathResource;
import com.mySpring.core.io.FileSystemResource;
import com.mySpring.core.io.Resource;

public class ResoureTest {

	//classpath的测试
	@Test
	
	public void testClassPathResource()throws Exception{
		Resource r = new ClassPathResource("petstore-v1.xml");
		InputStream is = null;
		try {
			is = r.getInputStream();
			//仅仅断言是否为空其实并不充分!
			Assert.assertNotNull(is);
		} finally {
			if(is != null){
				is.close();
			}
		}
	}
	
	
	//fileSystemResource测试
	public void testFileSytemResource() throws Exception{
		Resource r = new FileSystemResource("H:/Spring/mySpring/src/test/resources/petstore-v1.xml");
		InputStream is = null;
		try {
			is = r.getInputStream();
			//仅仅断言是否为空其实并不充分!
			Assert.assertNotNull(is);
		} finally {
			if(is != null){
				is.close();
			}
		}
	}
	
}
