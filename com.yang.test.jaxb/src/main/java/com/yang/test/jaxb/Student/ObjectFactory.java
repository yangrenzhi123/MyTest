package com.yang.test.jaxb.Student;

import javax.xml.bind.annotation.XmlRegistry;

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
}