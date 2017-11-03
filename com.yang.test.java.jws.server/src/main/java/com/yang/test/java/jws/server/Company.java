package com.yang.test.java.jws.server;

import javax.xml.bind.annotation.XmlElement;

@SuppressWarnings("restriction")
public class Company {

	@XmlElement(namespace = "http://www.xikesoft.com/")
	private String CompanyAccountID;
	@XmlElement(namespace = "http://www.xikesoft.com/")
	private String TenantCode;
	@XmlElement(namespace = "http://www.xikesoft.com/")
	private String CompanyTenantCode;
	@XmlElement(namespace = "http://www.xikesoft.com/")
	private String UserName;
	@XmlElement(namespace = "http://www.xikesoft.com/")
	private String Password;
	@XmlElement(namespace = "http://www.xikesoft.com/")
	private String CompanyName;
	@XmlElement(namespace = "http://www.xikesoft.com/")
	private String CompanyAddress;
	@XmlElement(namespace = "http://www.xikesoft.com/")
	private String ContactPerson;
	@XmlElement(namespace = "http://www.xikesoft.com/")
	private String Email;
	@XmlElement(namespace = "http://www.xikesoft.com/")
	private String Telphone;
	@XmlElement(namespace = "http://www.xikesoft.com/")
	private String Phone;
	@XmlElement(namespace = "http://www.xikesoft.com/")
	private String Status;
	@XmlElement(namespace = "http://www.xikesoft.com/")
	private String CreateTime;
	@XmlElement(namespace = "http://www.xikesoft.com/")
	private String CreatePeople;
	@XmlElement(namespace = "http://www.xikesoft.com/")
	private String LastModifyTime;
	@XmlElement(namespace = "http://www.xikesoft.com/")
	private String LastModifyPeople;
	@XmlElement(namespace = "http://www.xikesoft.com/")
	private String IsAuth;
	@XmlElement(namespace = "http://www.xikesoft.com/")
	private String AllowSeeStaffHealth;
	@XmlElement(namespace = "http://www.xikesoft.com/")
	private String AllowPublicUserReserve;
	@XmlElement(namespace = "http://www.xikesoft.com/")
	private String LastLoginTime;
	@XmlElement(namespace = "http://www.xikesoft.com/")
	private String isPwdEncoding;
	@XmlElement(namespace = "http://www.xikesoft.com/")
	private String CompanyCode;
}