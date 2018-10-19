package com.yang.test.java.validate;

import java.util.List;

public class BankBusinessValidateException extends RuntimeException {

	private static final long serialVersionUID = 4946850928171941060L;

	private List<Message> messages;

	public BankBusinessValidateException() {
	}

	public BankBusinessValidateException(List<Message> messages) {
		this.messages = messages;
	}

	public BankBusinessValidateException(List<Message> messages, String content) {
		super(content);
		this.messages = messages;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}