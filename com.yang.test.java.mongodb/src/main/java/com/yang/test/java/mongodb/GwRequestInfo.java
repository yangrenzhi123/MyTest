package com.yang.test.java.mongodb;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GwRequestInfo {

	private Length length;

	public Length getLength() {
		return length;
	}

	public void setLength(Length length) {
		this.length = length;
	}
}