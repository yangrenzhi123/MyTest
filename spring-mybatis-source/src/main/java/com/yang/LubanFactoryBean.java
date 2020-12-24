package com.yang;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

import com.yang.dao.UserMapper;

@Component
public class LubanFactoryBean implements FactoryBean<Object> {

	public Object getObject() throws Exception {
		return Proxy.newProxyInstance(LubanFactoryBean.class.getClassLoader(), new Class[] {UserMapper.class}, new InvocationHandler() {
			
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				System.out.println("test..");
				return null;
			}
		});
	}

	public Class<?> getObjectType() {
		return UserMapper.class;
	}
}
