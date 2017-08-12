package com.yang.test.jaxb.xml2obj2;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {

	public Human createHuman() {
		return new Human();
	}
}