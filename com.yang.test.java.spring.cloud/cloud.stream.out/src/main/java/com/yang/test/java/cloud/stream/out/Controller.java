package com.yang.test.java.cloud.stream.out;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

	@Autowired
	IMessageProvider messageProvider;

	@RequestMapping("/")
	public @ResponseBody void getSentence() {
		messageProvider.send("123");
	}
}