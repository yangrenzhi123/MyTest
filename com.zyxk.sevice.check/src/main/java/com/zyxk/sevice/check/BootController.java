package com.zyxk.sevice.check;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@EnableAutoConfiguration
public class BootController {

	@RequestMapping("/")
	@ResponseBody
	Boolean home() {
		return Checkor.scaning;
	}

	public static void main(String[] args) throws Exception {
		new Thread(new Runnable() {
			public void run() {
				Checkor.justDo();
			}
		}).start();
		
		
		SpringApplication.run(BootController.class, args);
	}
}