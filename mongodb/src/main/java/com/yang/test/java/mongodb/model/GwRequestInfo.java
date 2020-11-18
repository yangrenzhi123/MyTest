package com.yang.test.java.mongodb.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GwRequestInfo {
	
	private Length length;
	private String URI;
	private CostTime costTime;

	public String getURI() {
		return URI;
	}
	public void setURI(String uRI) {
		URI = uRI;
	}
	public Length getLength() {
		return length;
	}
	public void setLength(Length length) {
		this.length = length;
	}
	public CostTime getCostTime() {
		return costTime;
	}
	public void setCostTime(CostTime costTime) {
		this.costTime = costTime;
	}
}