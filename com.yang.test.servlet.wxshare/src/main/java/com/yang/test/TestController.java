package com.yang.test;

import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@SuppressWarnings("deprecation")
@Controller
public class TestController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String iframeTest2(Model model) {
		String uuid = UUID.randomUUID().toString();
		String timestamp = Long.toString(System.currentTimeMillis() / 1000);
		model.addAttribute("nonceStr", uuid);
		model.addAttribute("timestamp", timestamp);
		
		String signature = DigestUtils.shaHex("jsapi_ticket=HoagFKDcsGMVCIY2vOjf9sTpVMbQ4kfaVd_UnjEDtjzMPG_GfL2z3EYhIYyrDrJF3tNcROy09MHRbafe43UtFg&noncestr="+uuid+"&timestamp="+timestamp+"&url=http://www.yaoee.com:8080/");
		model.addAttribute("signature", signature);
		return "/iframeTest2";
	}
}