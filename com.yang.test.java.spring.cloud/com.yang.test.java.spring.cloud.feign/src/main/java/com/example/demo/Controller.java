package com.example.demo;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

	@Autowired
	private NounClient nounClient;

	@Autowired
	private WordService wordService;
	

	@RequestMapping("/")
	public @ResponseBody String getSentence() {
		return nounClient.getWord(UUID.randomUUID().toString());
	}
	
	@RequestMapping("/strix")
	public @ResponseBody String getS() {
		return wordService.getNoun();
	}
}