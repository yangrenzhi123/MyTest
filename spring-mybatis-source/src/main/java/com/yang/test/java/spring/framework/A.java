package com.yang.test.java.spring.framework;

import javax.annotation.PostConstruct;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Component;

@Component
public class A implements BeanNameAware, BeanFactoryAware {

	private String beanName;

	public String getBeanName() {
		return beanName;
	}
	@Override
	public void setBeanName(String name) {
		this.beanName = name;
	}
	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
	}
	@PostConstruct
	public void init() {
	}
}