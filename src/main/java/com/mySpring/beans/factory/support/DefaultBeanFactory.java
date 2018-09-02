package com.mySpring.beans.factory.support;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.mySpring.beans.PropertyValue;
import com.mySpring.context.support.BeanDefinitionValueResolver;
import com.mySpring.util.ClassUtils;

import com.mySpring.beans.BeanDefinition;
import com.mySpring.beans.factory.BeanCreationException;
import com.mySpring.beans.factory.config.ConfigurableBeanFactory;

public class DefaultBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory,BeanDefinitionRegistry{

	//没有考虑线程安全方面的情况，简单实现，使用map保存
	private final Map<String,BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>(64);
	private ClassLoader beanClassLoader;

	public DefaultBeanFactory(){}
	
	public BeanDefinition getBeanDefinition(String beanID) {
		return this.beanDefinitionMap.get(beanID);
	}

	public void registerBeanDefinition(String beanID, BeanDefinition bd) {
		this.beanDefinitionMap.put(beanID,bd);
	}
	
	public Object getBean(String beanID) {
		BeanDefinition bd = this.getBeanDefinition(beanID);
		if(bd == null){
			return null;
		}
		
		if(bd.isSingleton()){
			Object bean = this.getSigleton(beanID);
			if(bean == null){
				bean = createBean(bd);
				this.registerSingleton(beanID, bean);
			}
			return bean;
		}
		return createBean(bd);
	}

	private Object createBean(BeanDefinition bd){
		//创建实例
		Object bean = instantiateBean(bd);
		//设置属性
		populateBean(bd,bean);
		return bean;
	}

	protected void populateBean(BeanDefinition bd, Object bean) {
		List<PropertyValue> pvs = bd.getPropertyValues();
		if(pvs == null || pvs.isEmpty()){
			return;
		}
		BeanDefinitionValueResolver valueResolver = new BeanDefinitionValueResolver(this);
		try {
			for (PropertyValue pv:pvs) {
				String propertyName = pv.getName();
				Object originalValue = pv.getValue();
				Object resolvedValue = valueResolver.resolverValueIfNecessary(originalValue);
				//加入现在originalValue 表示的是ref=acountDao,已经通过resolver得到了acountDao 对象
				//接下来怎么办，如何去调用PetStore 的setAcountDao方法？
				BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
				PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
				for (PropertyDescriptor pd:pds) {
					if(pd.getName().equals(propertyName)){
						pd.getWriteMethod().invoke(bean,resolvedValue);
						break;
					}

				}
			}
		}catch (Exception ex){
			throw  new BeanCreationException("Failed to obtain BeanInfo for class ["+bd.getBeanClassName()+"]");
		}
	}

	private Object instantiateBean(BeanDefinition bd) {
		ClassLoader cl = this.getBeanClassLoader();
		String beanClassName = bd.getBeanClassName();
		try {
			Class<?> clz = cl.loadClass(beanClassName);
			return clz.newInstance();
		} catch (Exception e) {
			throw new BeanCreationException("create bean for "+beanClassName+"failed",e );
		}
	}

	public void setBeanClassLoader(ClassLoader beanClassLoader) {
		this.beanClassLoader = beanClassLoader;
		
	}

	public ClassLoader getBeanClassLoader() {
		return (this.beanClassLoader !=null ? this.beanClassLoader:ClassUtils.getDefaultClassLoader());
	}

}
