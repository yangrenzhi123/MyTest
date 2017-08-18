package com.yang.test.java.jws.client;

import www.xikesoft.com.WebServiceTest;
import www.xikesoft.com.WebServiceTestService;

public class Test {

	public static void main(String[] args) {
		WebServiceTest t = new WebServiceTestService().getWebServiceTestPort();
		
		System.out.println(t.getValue("123"));
	}
}