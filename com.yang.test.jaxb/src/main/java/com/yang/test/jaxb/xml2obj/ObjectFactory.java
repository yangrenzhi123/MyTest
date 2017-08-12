package com.yang.test.jaxb.xml2obj;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {

	public Human2 createHuman() {
		return new Human2();
	}
}