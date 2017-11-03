package com.yang.test.java.jws.server;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;

@SuppressWarnings("restriction")
@WebService(targetNamespace = "http://www.xikesoft.com/")
public class WebServiceTest {

	public static void main(String[] args) {
		Endpoint.publish("http://localhost:801/UploadService.asmx", new WebServiceTest());
	}

	public String AccountInfo(@WebParam(name = "_user", targetNamespace = "http://www.xikesoft.com/") User _user,
			@WebParam(name = "_account", targetNamespace = "http://www.xikesoft.com/") Account _account,
			@WebParam(name = "_company", targetNamespace = "http://www.xikesoft.com/") Company _company,
			@WebParam(name = "isSendSms", targetNamespace = "http://www.xikesoft.com/") Boolean isSendSms) {
		return "";
	}
}