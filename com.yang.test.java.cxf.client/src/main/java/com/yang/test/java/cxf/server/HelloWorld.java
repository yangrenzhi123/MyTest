package com.yang.test.java.cxf.server;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface HelloWorld {

	String sayHi(@WebParam(name="text")String text);
}