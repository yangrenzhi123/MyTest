package com.yang.test.java;

public class UserServiceTest {

	public static void main(String[] args) {
		UserServiceProxy pro = new UserServiceProxy();
		UserServiceBean bean = new UserServiceBean("wuq");
		UserService se = (UserService) pro.createProxyIntance(bean);
		se.say();
	}

}
