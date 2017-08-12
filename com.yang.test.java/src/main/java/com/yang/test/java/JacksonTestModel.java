package com.yang.test.java;

import org.codehaus.jackson.annotate.JsonProperty;

public class JacksonTestModel {

	@JsonProperty(value = "Id")
	private Integer id;

	private TestEnum test;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTest() {
		return test.ordinal();
	}

	public void setTest(TestEnum test) {
		this.test = test;
	}
}