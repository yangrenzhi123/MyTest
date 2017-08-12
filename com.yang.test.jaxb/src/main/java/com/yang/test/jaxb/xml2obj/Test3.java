package com.yang.test.jaxb.xml2obj;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.stream.StreamSource;

import org.xml.sax.SAXException;

/**
 * 将xml解析成为Object。另外，在namespace不正常的情况下，也提供了解决放发。
 */
public class Test3 {

	public static void main(String[] args) throws JAXBException, SAXException, ParserConfigurationException {
		String xml = "<?xml version=\"1.0\" encoding=\"GBK\" standalone=\"yes\"?><Human2 xmlns=\"test.yang.com\"><GENDER>femal</GENDER><NAME>alice</NAME></Human2>";

		StreamSource streamSource = new StreamSource(new StringReader(xml));
		JAXBContext jaxbRes = JAXBContext.newInstance(Human2.class.getPackage().getName(), Human2.class.getClassLoader());
		Unmarshaller unmarshaller = jaxbRes.createUnmarshaller();
		JAXBElement<Human2> reponseElement = unmarshaller.unmarshal(streamSource, Human2.class);
		Human2 alice = reponseElement.getValue();

		// 解析xml时无视命名空间，用的方式是sax.setNamespaceAware(false);
//		JAXBContext jaxbContext = JAXBContext.newInstance(Human2.class.getPackage().getName(), Human2.class.getClassLoader());
//		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
//		StringReader reader = new StringReader(xml);
//		SAXParserFactory sax = SAXParserFactory.newInstance();
//		sax.setNamespaceAware(false);
//		XMLReader xmlReader = sax.newSAXParser().getXMLReader();
//		Source source = new SAXSource(xmlReader, new InputSource(reader));
//		Human2 alice = (Human2) unmarshaller.unmarshal(source);

		// 解析xml时无视命名空间，用的方式是filter
//		String xml2 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><Human2 xmlns=\"test.yang.com\"><GENDER>female</GENDER><NAME>alice</NAME></Human2>";
//		Human2 alice2 = XmlUtil.fromXML(xml2, Human2.class);

		System.out.println(alice.getName());
	}
}