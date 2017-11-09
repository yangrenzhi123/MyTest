package com.yang.test.java.jws.server;

import javax.xml.bind.annotation.XmlElement;

@SuppressWarnings("restriction")
public class Str {

	@XmlElement(namespace = "http://www.xikesoft.com/")
	public String string;
}