package com.example.demo;

import org.bouncycastle.crypto.RuntimeCryptoException;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class Controller {

	@RequestMapping("/")
	public String getWord() throws InterruptedException {
		return "0";
	}

	@RequestMapping("/formdata")
	public String test() throws InterruptedException {
		Integer a = null;
		if(a.equals("")) {
			
		}
		
		return "0";
	}
}