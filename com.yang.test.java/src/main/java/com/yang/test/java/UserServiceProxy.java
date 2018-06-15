package com.yang.test.java;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class UserServiceProxy implements InvocationHandler {
	private Object targetObject;

	public Object createProxyIntance(Object targetObject) {
		this.targetObject = targetObject;
		return Proxy.newProxyInstance(this.targetObject.getClass().getClassLoader(), this.targetObject.getClass().getInterfaces(), this);
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println(123);
		
		UserServiceBean bean = (UserServiceBean) this.targetObject;
		Object obj = null;
		if (bean.getName() != null) {
			if (bean.getName().equals("wuq")) {
				obj = method.invoke(this.targetObject, args);
			} else {
				System.out.println("您没有操作权限！");
			}
		} else {
			System.out.println("您没有操作权限！");
		}
		return obj;
	}
}