package com.yang.test.java.jws.client;

import www.xikesoft.com.WebServiceTest;
import www.xikesoft.com.WebServiceTestDto;
import www.xikesoft.com.WebServiceTestService;

public class Test {

	public static void main(String[] args) {
		WebServiceTest t = new WebServiceTestService().getWebServiceTestPort();
		WebServiceTestDto d = new WebServiceTestDto();
		d.setLevel(1);
		
		
		System.out.println(t.accountInfo(d));
	}
}