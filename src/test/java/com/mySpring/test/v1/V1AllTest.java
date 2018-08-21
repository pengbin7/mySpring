package com.mySpring.test.v1;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	ApplicationContextTest.class,
	BeanFactoryTest.class,
	ResoureTest.class
})
public class V1AllTest {

}
