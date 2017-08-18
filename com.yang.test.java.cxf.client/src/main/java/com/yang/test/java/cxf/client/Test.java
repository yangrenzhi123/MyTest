package com.yang.test.java.cxf.client;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import com.yang.test.java.cxf.server.HelloWorld;
public class Test {

	public static void main(String[] args) {

        JaxWsProxyFactoryBean svr = new JaxWsProxyFactoryBean();
        svr.setServiceClass(HelloWorld.class);
        svr.setAddress("http://localhost:8080/helloWorld");
        HelloWorld hw = (HelloWorld) svr.create();;
        System.out.println(hw.sayHi("you"));
	}
}