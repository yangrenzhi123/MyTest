package com.yang.test.jaxb.obj2xml.with.ns2;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 * 自定义命名空间(xmlns:hum)
 */
public class Test2 {

	public static void main(String[] args) throws JAXBException {
		Human alice = new Human();
		alice.setGender("female");
		alice.setName("alice");

		JAXBContext jaxb = JAXBContext.newInstance(Human.class.getPackage().getName());
		Marshaller marshaller = jaxb.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_ENCODING, "GBK");
		StringWriter strWriter = new StringWriter();
		marshaller.marshal(alice, strWriter);
		System.out.println(strWriter.toString());
	}
}