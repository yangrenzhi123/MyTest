package com.yang.test.jaxb.xml2obj2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "HUMAN")
public class Human {

	@XmlElement(name = "GENDER", defaultValue = "WOMEN", namespace = "test.yang.com")
	private String gender;

	@XmlElement(name = "NAME", required = true, nillable = true)
	private String name;

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}