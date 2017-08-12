package com.yang.test.java.webservice;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;

@WebService
public class WebServiceTest {

	public String doTest(String value) {
		return value;
	}

	public static void main(String[] args) {
		Endpoint.publish("http://localhost:9001/WebService/Test/doTest", new WebServiceTest());
	}
}