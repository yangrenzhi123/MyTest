package com.zyxk.sevice.check.filter;

import org.springframework.web.util.NestedServletException;

public class TipException extends NestedServletException {

	private static final long serialVersionUID = 7827444065800695635L;
	private Integer code;

	public TipException(Integer code, String message) {
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