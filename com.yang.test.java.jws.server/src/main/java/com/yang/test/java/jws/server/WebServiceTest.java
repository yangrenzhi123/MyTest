package com.yang.test.java.jws.server;

import javax.jws.WebService;
import javax.jws.WebParam;
import javax.xml.ws.Endpoint;



//@WebService(name="ComplexPortType", serviceName="ComplexService", targetNamespace="http://example.org")
//name对应接口类名，serviceName对应client实现类名
@WebService(targetNamespace="http://www.xikesoft.com/")
public class WebServiceTest {

	public static void main(String[] args) {
		Endpoint.publish("http://192.168.6.152:9001/UploadService.asmx", new WebServiceTest());
	}
	
	public String AccountInfo(@WebParam(targetNamespace="http://www.xikesoft.com/") Model t){
		return "";
	}
}