package com.yang.test.jaxb.obj2xml.non.ns;

import javax.xml.bind.annotation.XmlRegistry;

import com.yang.test.jaxb.obj2xml.with.ns.Human;

@XmlRegistry
public class ObjectFactory {

	public ObjectFactory() {
	}

	public Book createBook() {
		return new Book();
	}

	public Books createBooks() {
		return new Books();
	}

	public Human createHuman() {
		return new Human();
	}
}