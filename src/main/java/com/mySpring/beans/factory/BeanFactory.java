package com.mySpring.beans.factory;

import com.mySpring.beans.BeanDefinition;
import com.mySpring.service.v1.PetStoreService;

public interface BeanFactory {

	BeanDefinition getBeanDefinition(String string);

	Object getBean(String string);

}
