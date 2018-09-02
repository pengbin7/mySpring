package com.mySpring.test.v2;

import com.mySpring.beans.factory.config.RuntimeBeanReference;
import com.mySpring.beans.factory.config.TypedStringValue;
import com.mySpring.beans.factory.support.DefaultBeanFactory;
import com.mySpring.beans.factory.xml.XmlBeanDefinitionReader;
import com.mySpring.context.support.BeanDefinitionValueResolver;
import com.mySpring.core.io.ClassPathResource;
import com.mySpring.dao.v2.AcountDao;
import org.junit.Assert;
import org.junit.Test;
public class BeanDefinitionValueResolverTest {

    @Test
    public void testResolverRuntimeBeanReference(){
        DefaultBeanFactory factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(new ClassPathResource("petStore-v2.xml"));

        BeanDefinitionValueResolver resolver = new BeanDefinitionValueResolver(factory);
        RuntimeBeanReference ref = new RuntimeBeanReference("acountDao");
        Object value = resolver.resolverValueIfNecessary(ref);

        Assert.assertNotNull(value);
        Assert.assertTrue(value instanceof AcountDao);
    }

    @Test
    public void testResolverTypedStringValue(){
        DefaultBeanFactory factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(new ClassPathResource("petStore-v2.xml"));

        BeanDefinitionValueResolver resolver = new BeanDefinitionValueResolver(factory);
        TypedStringValue typedStringValue = new TypedStringValue("test");
        Object value = resolver.resolverValueIfNecessary(typedStringValue);

        Assert.assertNotNull(value);
        Assert.assertEquals("test",value);
    }
}
