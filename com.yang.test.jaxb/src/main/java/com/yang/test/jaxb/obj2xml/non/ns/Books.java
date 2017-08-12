package com.yang.test.jaxb.obj2xml.non.ns;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Books")
public class Books {

	private List<Book> row;

	public Books() {
		super();
	}

	public Books(List<Book> row) {
		super();
		this.row = row;
	}

	public List<Book> getRow() {
		return row;
	}

	public void setRow(List<Book> row) {
		this.row = row;
	}

}