package com.yang.test.java.validate;

public class Message {

	private ValidateType type;

	private String content;

	public Message() {
		super();
	}

	public Message(String content) {
		super();
		this.content = content;
	}

	public Message(ValidateType type, String content) {
		super();
		this.type = type;
		this.content = content;
	}

	public ValidateType getType() {
		return type;
	}

	public void setType(ValidateType type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}