package com.yang.test.jaxb.obj2xml.non.ns;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class Test {

	public static void main(String[] args) throws JAXBException {
		Book book1 = new Book("钢铁是怎样炼成的");
		Book book2 = new Book("熟练煮鸡蛋");
		List<Book> rows = new ArrayList<Book>();
		rows.add(book1);
		rows.add(book2);
		Books books = new Books(rows);
		
		
		JAXBContext jaxb = JAXBContext.newInstance(Books.class.getPackage().getName());
		Marshaller marshaller = jaxb.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_ENCODING, "GBK");
		StringWriter strWriter = new StringWriter();
		marshaller.marshal(books, strWriter);
		System.out.println(strWriter.toString());
	}
}