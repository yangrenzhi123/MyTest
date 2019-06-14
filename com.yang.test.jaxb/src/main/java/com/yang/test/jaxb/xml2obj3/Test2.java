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
public class Test2 {

	public static void main(String[] args) throws JAXBException, SAXException, ParserConfigurationException {
		String xml = 
		"<?xml version=\"1.0\" encoding=\"utf-8\"?>"+
		"<messages>"+
		"  <switchset> "+
		"    <visitor> "+
		"      <sourceorgan>3301100000000000000000</sourceorgan>  "+
		"      <sourcedomain>3301000017</sourcedomain> "+
		"    </visitor>  "+
		"    <serviceinf> "+
		"      <servicecode>10002701</servicecode> "+
		"    </serviceinf>  "+
		"    <provider> "+
		"      <targetorgan/>  "+
		"      <targetdomain/> "+
		"    </provider>  "+
		"    <route/>  "+
		"    <process/> "+ 
		"    <switchmessage> "+
		"      <messagecode/> "+ 
		"      <messagetext/> "+
		"    </switchmessage> "+
		"  </switchset>  "+
		"  <business> "+
		"    <standardcode/> "+ 
		"    <returnmessage> "+
		"      <retcode>1</retcode>  "+
		"      <rettext/> "+
		"    </returnmessage>  "+
		"    <returnset> "+
		"      <rettotal>1</rettotal>  "+
		"      <retpaging>0</retpaging>  "+
		"      <retpageindex>-1</retpageindex>  "+
		"      <retpageset>0</retpageset> "+
		"    </returnset>  "+
		"    <datacompress>0</datacompress>  "+
		"    <businessdata> "+
		"      <datasets> "+
		"        <setdetails> "+
		"          <WS99_00_913_02>f1a0b0f9-cc48-4cb6-b76a-efd08d12abe0</WS99_00_913_02> "+
		"          <WS99_00_913_02>f1a0b0f9-cc48-4cb6-b76a-efd08d12abe055</WS99_00_913_02> "+
		"        </setdetails>  "+
		"        <settype/>  "+
		"        <setcode/> "+
		"      </datasets> "+
		"    </businessdata> "+
		"  </business>  "+
		"  <extendset> "+
		"    <token>H4sIAAAAAAAAAA2KOxLCMAwFD0PtmaefI5eEhophQpNOo4B1/ yPgZovdzYvLvnDPqup1DUlVXzDW ocW36r/kaWg+nRrRHC1Fq20+O2NdZBJB1mFgVQM04nU833cAstLj2M/ zs0eIgJYDbX8hAS9MdQAA AA==</token> "+
		"  </extendset> "+
		"</messages>";

		StreamSource streamSource = new StreamSource(new StringReader(xml));
		JAXBContext jaxbRes = JAXBContext.newInstance(PackageCodeList.class.getPackage().getName(), PackageCodeList.class.getClassLoader());
		Unmarshaller unmarshaller = jaxbRes.createUnmarshaller();
		JAXBElement<Messages> reponseElement = unmarshaller.unmarshal(streamSource, Messages.class);
		Messages alice = reponseElement.getValue();

		System.out.println(alice);
	}
}