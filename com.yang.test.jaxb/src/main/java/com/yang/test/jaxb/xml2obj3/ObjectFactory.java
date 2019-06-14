package com.yang.test.jaxb.xml2obj3;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {

	public PackageCodeList createPackageCodeList() {
		return new PackageCodeList();
	}

	public Messages createMessages() {
		return new Messages();
	}
}