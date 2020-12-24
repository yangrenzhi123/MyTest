package com.yang.test.java.spring.framework;

import org.springframework.beans.factory.BeanNameAware;

public class A implements BeanNameAware {

	private String beanName;

	public void setBeanName(String name) {
		this.beanName = name;
	}

	public String getBeanName() {
		return beanName;
	}
}