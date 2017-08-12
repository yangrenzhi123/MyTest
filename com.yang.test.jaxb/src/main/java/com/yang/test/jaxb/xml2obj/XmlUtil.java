package com.yang.test.jaxb.xml2obj;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.*;
import javax.xml.transform.sax.SAXSource;

import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLFilterImpl;
import org.xml.sax.helpers.XMLReaderFactory;

public class XmlUtil {

	public static String toXML(Object obj) {
		try {
			JAXBContext context = JAXBContext.newInstance(obj.getClass());

			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");// //编码格式
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);// 是否格式化生成的xml串
			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, false);// 是否省略xm头声明信息

			StringWriter out = new StringWriter();
			OutputFormat format = new OutputFormat();
			format.setIndent(true);
			format.setNewlines(true);
			format.setNewLineAfterDeclaration(false);
			XMLWriter writer = new XMLWriter(out, format);

			XMLFilterImpl nsfFilter = new XMLFilterImpl() {
				private boolean ignoreNamespace = false;
				private String rootNamespace = null;
				private boolean isRootElement = true;

				@Override
				public void startDocument() throws SAXException {
					super.startDocument();
				}

				@Override
				public void startElement(String uri, String localName, String qName, Attributes atts)
						throws SAXException {
					if (this.ignoreNamespace)
						uri = "";
					if (this.isRootElement)
						this.isRootElement = false;
					else if (!uri.equals("") && !localName.contains("xmlns"))
						localName = localName + " xmlns=\"" + uri + "\"";

					super.startElement(uri, localName, localName, atts);
				}

				@Override
				public void endElement(String uri, String localName, String qName) throws SAXException {
					if (this.ignoreNamespace)
						uri = "";
					super.endElement(uri, localName, localName);
				}

				@Override
				public void startPrefixMapping(String prefix, String url) throws SAXException {
					if (this.rootNamespace != null)
						url = this.rootNamespace;
					if (!this.ignoreNamespace)
						super.startPrefixMapping("", url);

				}
			};
			nsfFilter.setContentHandler(writer);
			marshaller.marshal(obj, nsfFilter);
			return out.toString();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static <T> T fromXML(String xml, Class<T> valueType) {
		try {
			JAXBContext context = JAXBContext.newInstance(valueType);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			// return (T) unmarshaller.unmarshal(new StringReader(xml));
			XMLReader reader = XMLReaderFactory.createXMLReader();
			XMLFilterImpl nsfFilter = new XMLFilterImpl() {
				private boolean ignoreNamespace = true;

				@Override
				public void startDocument() throws SAXException {
					super.startDocument();
				}

				@Override
				public void startElement(String uri, String localName, String qName, Attributes atts)
						throws SAXException {
					if (this.ignoreNamespace)
						uri = "";
					super.startElement(uri, localName, qName, atts);
				}

				@Override
				public void endElement(String uri, String localName, String qName) throws SAXException {
					if (this.ignoreNamespace)
						uri = "";
					super.endElement(uri, localName, localName);
				}

				@Override
				public void startPrefixMapping(String prefix, String url) throws SAXException {
					if (!this.ignoreNamespace)
						super.startPrefixMapping("", url);
				}
			};
			nsfFilter.setParent(reader);
			InputSource input = new InputSource(new StringReader(xml));
			SAXSource source = new SAXSource(nsfFilter, input);
			return (T) unmarshaller.unmarshal(source);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
}