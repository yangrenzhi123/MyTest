package com.yang.test.java.cxf.server;

import javax.xml.ws.Endpoint;
public class Test {

	public static void main(String[] args) {

		HelloWorldImpl implementor = new HelloWorldImpl();
		String address = "http://192.168.6.152:8080/helloWorld";
		Endpoint.publish(address, implementor);
		System.out.println("web service started");
	}
}