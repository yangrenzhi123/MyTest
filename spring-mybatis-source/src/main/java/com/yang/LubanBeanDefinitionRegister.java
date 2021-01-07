package com.yang;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import com.yang.dao.OrderMapper;
import com.yang.dao.UserMapper;

@SuppressWarnings("rawtypes")
public class LubanBeanDefinitionRegister implements ImportBeanDefinitionRegistrar {

	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry, BeanNameGenerator importBeanNameGenerator) {
		List<Class> mappers = new ArrayList<>();
		mappers.add(UserMapper.class);
		mappers.add(OrderMapper.class);
		
		for(Class mapper : mappers) {
			BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition();
			
			BeanDefinition bd = builder.getBeanDefinition();
			bd.setBeanClassName("com.yang.LubanFactoryBean");
			bd.getConstructorArgumentValues().addGenericArgumentValue(mapper);
			
			registry.registerBeanDefinition(mapper.getName(), bd);
		}
	}
}