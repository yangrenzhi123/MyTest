package com.yang.test.servlet.springmvc.poi;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DownloadController {

	@RequestMapping(value = "/download", method = RequestMethod.GET)
	@ResponseBody
	public void accept() {

	}
}