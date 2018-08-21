package com.mySpring.context;

import com.mySpring.beans.factory.config.ConfigurableBeanFactory;

public interface ApplicationContext extends ConfigurableBeanFactory{

	Object getBean(String string);

}
