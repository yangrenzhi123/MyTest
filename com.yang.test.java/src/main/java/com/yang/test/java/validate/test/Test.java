package com.yang.test.java.validate.test;

import java.util.ArrayList;
import java.util.List;

import com.yang.test.java.validate.Message;
import com.yang.test.java.validate.Validator;

public class Test {

	public static void main(String[] args) {
		PayInnerRequest request=new PayInnerRequest();
		List<Message> messages = new ArrayList<Message>();
		messages.addAll(Validator.doValidate(PayInnerRequest.class, request));
		
		System.out.println(1);
	}
}