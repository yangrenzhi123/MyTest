package com.yang.test.jaxb.xml2obj;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Source;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamSource;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import com.yang.test.jaxb.obj2xml.with.ns.Human;

/**
 * 将xml解析成为Object。另外，在namespace不正常的情况下，也提供了解决放发。
 */
public class Test3 {

	public static void main(String[] args) throws JAXBException, SAXException, ParserConfigurationException {
		String xml = "<?xml version=\"1.0\" encoding=\"GBK\" standalone=\"yes\"?><HUMAN xmlns=\"test.yang.com\"><GENDER>femal</GENDER><NAME>alice</NAME></HUMAN>";

		StreamSource streamSource = new StreamSource(new StringReader(xml));
		JAXBContext jaxbRes = JAXBContext.newInstance(Human.class.getPackage().getName(), Human.class.getClassLoader());
		Unmarshaller unmarshaller = jaxbRes.createUnmarshaller();
		JAXBElement<Human> reponseElement = unmarshaller.unmarshal(streamSource, Human.class);
		Human alice = reponseElement.getValue();

		// 解析xml时无视命名空间，用的方式是sax.setNamespaceAware(false);
//		JAXBContext jaxbContext = JAXBContext.newInstance(Human.class.getPackage().getName(), Human.class.getClassLoader());
//		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
//		StringReader reader = new StringReader(xml);
//		SAXParserFactory sax = SAXParserFactory.newInstance();
//		sax.setNamespaceAware(false);
//		XMLReader xmlReader = sax.newSAXParser().getXMLReader();
//		Source source = new SAXSource(xmlReader, new InputSource(reader));
//		Human alice = (Human) unmarshaller.unmarshal(source);

		// 解析xml时无视命名空间，用的方式是filter
		String xml2 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><HUMAN xmlns=\"test.yang.com\"><GENDER>female</GENDER><NAME>alice</NAME></HUMAN>";
		Human alice2 = XmlUtil.fromXML(xml2, Human.class);

		System.out.println(alice.getName());
	}
}