package com.yang;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.springframework.beans.factory.FactoryBean;

@SuppressWarnings("rawtypes")
public class LubanFactoryBean implements FactoryBean<Object> {

	private Class mapper;
	public LubanFactoryBean(Class mapper) {
		super();
		this.mapper = mapper;
	}

	public Object getObject() throws Exception {
		return Proxy.newProxyInstance(LubanFactoryBean.class.getClassLoader(), new Class[] {mapper}, new InvocationHandler() {
			
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				System.out.println("test..");
				return null;
			}
		});
	}

	public Class<?> getObjectType() {
		return mapper;
	}
}