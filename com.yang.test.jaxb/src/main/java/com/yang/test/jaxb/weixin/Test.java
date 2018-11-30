package com.yang.test.jaxb.weixin;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.stream.StreamSource;

import org.xml.sax.SAXException;

public class Test {

	public static void main(String[] args) throws JAXBException, SAXException, ParserConfigurationException {
		String xml = "<xml><return_code><![CDATA[SUCCESS]]></return_code>"+
		"<return_msg><![CDATA[OK]]></return_msg>"+
		"<appid><![CDATA[wxcc79d67b43519897]]></appid>"+
		"<mch_id><![CDATA[1504805211]]></mch_id>"+
		"<device_info><![CDATA[WEB]]></device_info>"+
		"<nonce_str><![CDATA[3ofnwTFGeRnhvg5J]]></nonce_str>"+
		"<sign><![CDATA[82064968276BC366FA7687E7B72CA080]]></sign>"+
		"<result_code><![CDATA[SUCCESS]]></result_code>"+
		"<openid><![CDATA[oEynf0mYMCMtfLv1FfELfY2wBWV0]]></openid>"+
		"<is_subscribe><![CDATA[Y]]></is_subscribe>"+
		"<trade_type><![CDATA[JSAPI]]></trade_type>"+
		"<bank_type><![CDATA[CFT]]></bank_type>"+
		"<total_fee>3000</total_fee>"+
		"<fee_type><![CDATA[CNY]]></fee_type>"+
		"<transaction_id><![CDATA[4200000208201810056071769667]]></transaction_id>"+
		"<out_trade_no><![CDATA[20181005165754284023]]></out_trade_no>"+
		"<attach><![CDATA[]]></attach>"+
		"<time_end><![CDATA[20181005165759]]></time_end>"+
		"<trade_state><![CDATA[SUCCESS]]></trade_state>"+
		"<cash_fee>3000</cash_fee>"+
		"<trade_state_desc><![CDATA[123]]></trade_state_desc>"+
		"</xml>";
		
		
		
		

		StreamSource streamSource = new StreamSource(new StringReader(xml));
		JAXBContext jaxbRes = JAXBContext.newInstance(Result.class.getPackage().getName(), Result.class.getClassLoader());
		Unmarshaller unmarshaller = jaxbRes.createUnmarshaller();
		JAXBElement<Result> reponseElement = unmarshaller.unmarshal(streamSource, Result.class);
		Result result = reponseElement.getValue();
		
		System.out.println(result.getTrade_state());
	}
}