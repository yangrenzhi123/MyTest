package com.yang.test.jaxb.xml2obj3;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.stream.StreamSource;

import org.xml.sax.SAXException;

/**
			<packageCodeList>
				<string>0105</string>
				<string>0202</string>
			</packageCodeList>
			注意xml每个节点必须建一个类
 */
public class Test {

	public static void main(String[] args) throws JAXBException, SAXException, ParserConfigurationException {
		String xml = "<?xml version=\"1.0\" encoding=\"GBK\" standalone=\"yes\"?><packageCodeList><string>0105</string><string>0202</string><string>0302</string><string>0410</string><string>0702</string><string>0905</string><string>1010</string><string>1011</string><string>1012</string><string>1022</string><string>1040</string><string>1048</string><string>1052</string><string>1054</string><string>1056</string><string>1058</string><string>1208</string><string>1301</string><string>1840</string><string>1901</string><string>1902</string><string>3009</string><string>3025</string><string>3601</string><string>3602</string><string>3605</string><string>3801</string></packageCodeList>";

		StreamSource streamSource = new StreamSource(new StringReader(xml));
		JAXBContext jaxbRes = JAXBContext.newInstance(PackageCodeList.class.getPackage().getName(), PackageCodeList.class.getClassLoader());
		Unmarshaller unmarshaller = jaxbRes.createUnmarshaller();
		JAXBElement<PackageCodeList> reponseElement = unmarshaller.unmarshal(streamSource, PackageCodeList.class);
		PackageCodeList alice = reponseElement.getValue();

		System.out.println(alice);
	}
}