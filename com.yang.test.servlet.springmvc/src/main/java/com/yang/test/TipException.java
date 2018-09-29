package com.yang.test;

import org.springframework.web.util.NestedServletException;

public class TipException extends NestedServletException {

	private static final long serialVersionUID = 7827444065800695635L;
	private Integer code;

	public TipException(String message) {
		super(message);
	}

	public TipException(String message, Integer code) {
		super(message);
		this.code = code;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
}