package com.yang.test.jaxb.obj2xml.with.ns;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.yang.test.jaxb.xml2obj.XmlUtil;

/** 将Object解析成为xml，并附带命名空间 */
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
		
		System.out.println(XmlUtil.toXML(alice));
	}
}