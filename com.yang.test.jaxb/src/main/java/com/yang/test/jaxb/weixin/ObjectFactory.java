package com.yang.test.jaxb.weixin;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {

	public Result createResult() {
		return new Result();
	}
}