package com.yang.test.jaxb.obj2xml.with.ns2;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {

	public ObjectFactory() {
	}

	public Human createHuman() {
		return new Human();
	}
}