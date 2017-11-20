package com.yang.test.servlet.springmvc.kaoqin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class KaoQinController {

	@RequestMapping(value = "/kaoqin/accept", method = RequestMethod.POST)
	@ResponseBody
	public KaoQinReponse accept(@RequestBody KaoQinDto data) {
		KaoQinReponse result = new KaoQinReponse();
		result.setStatus(100);
		result.setStatusMessage("成功");
		return result;
	}
}