package com.yang.test.java.jws.server;

import javax.xml.bind.annotation.XmlElement;

@SuppressWarnings("restriction")
public class BasicInfo {

	@XmlElement(namespace = "http://www.xikesoft.com/")
	private String BSID;
	@XmlElement(namespace = "http://www.xikesoft.com/")
	private String AccountID;
	@XmlElement(namespace = "http://www.xikesoft.com/")
	private String National;
	@XmlElement(namespace = "http://www.xikesoft.com/")
	private String Telephone;
	@XmlElement(namespace = "http://www.xikesoft.com/")
	private String Company;
	@XmlElement(namespace = "http://www.xikesoft.com/")
	private String HomeAddress;
	@XmlElement(namespace = "http://www.xikesoft.com/")
	private String PostalCode;
	@XmlElement(namespace = "http://www.xikesoft.com/")
	private String ArchivingDate;
	@XmlElement(namespace = "http://www.xikesoft.com/")
	private String LastModifyTime;
}