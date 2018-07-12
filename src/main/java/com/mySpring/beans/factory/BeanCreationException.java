package com.mySpring.beans.factory;

import com.mySpring.beans.BeansException;

public class BeanCreationException extends BeansException{

	public BeanCreationException(String msg) {
		super(msg);
	}
	
	public BeanCreationException(String msg,Throwable cause){
		super(msg,cause);
	}

}
