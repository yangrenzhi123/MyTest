package com.yang.test.jaxb.xml2obj2;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.stream.StreamSource;

import org.xml.sax.SAXException;

/**
 * 本次解决xml字符串中默认命名空间(xmlns)，无法正常解析成Object里面的树据
 * 
 */
public class Test {

	public static void main(String[] args) throws JAXBException, SAXException, ParserConfigurationException {
		String xml = "<?xml version=\"1.0\" encoding=\"GBK\" standalone=\"yes\"?><Human xmlns=\"test.yang.com\"><GENDER>femal</GENDER><NAME>alice</NAME></Human>";

		StreamSource streamSource = new StreamSource(new StringReader(xml));
		JAXBContext jaxbRes = JAXBContext.newInstance(Human.class.getPackage().getName(), Human.class.getClassLoader());
		Unmarshaller unmarshaller = jaxbRes.createUnmarshaller();
		JAXBElement<Human> reponseElement = unmarshaller.unmarshal(streamSource, Human.class);
		Human alice = reponseElement.getValue();

		System.out.println(alice.getName());
	}
}