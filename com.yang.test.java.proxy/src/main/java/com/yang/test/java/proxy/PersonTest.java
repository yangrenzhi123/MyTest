package com.yang.test.java.proxy;

import java.lang.reflect.Proxy;

public class PersonTest {

	public static void main(String[] args) {
		PersonDao pDao = new PersonDaoImpl();
		PersonHandler handler = new PersonHandler(pDao);

		PersonDao proxy = (PersonDao) Proxy.newProxyInstance(pDao.getClass().getClassLoader(), pDao.getClass().getInterfaces(), handler);
		proxy.say();
	}
}