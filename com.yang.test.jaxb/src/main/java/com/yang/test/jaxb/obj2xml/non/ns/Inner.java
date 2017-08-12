package com.yang.test.jaxb.obj2xml.non.ns;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;


@XmlAccessorType(XmlAccessType.NONE)
public class Inner {

	@XmlElement(name = "Rule_Chk_Ind_1")
	private String i;

	public String getI() {
		return i;
	}

	public void setI(String i) {
		this.i = i;
	}

}