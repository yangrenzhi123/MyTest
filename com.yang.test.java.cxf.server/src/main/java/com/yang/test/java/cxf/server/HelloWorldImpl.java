package com.yang.test.java.cxf.server;


import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(endpointInterface="com.yang.test.java.cxf.server.HelloWorld",serviceName="HelloWorld")
public class HelloWorldImpl implements HelloWorld {

	public String sayHi(@WebParam(name="text", targetNamespace="http://server.cxf.java.test.yang.com/") String text) {
		return text;
	}

}
