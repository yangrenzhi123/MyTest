package com.yang.test;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@SuppressWarnings("deprecation")
@Controller
public class TestController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String iframeTest2(Model model, HttpServletRequest request) {
		String uuid = UUID.randomUUID().toString();
		String timestamp = Long.toString(System.currentTimeMillis() / 1000);
		model.addAttribute("nonceStr", uuid);
		model.addAttribute("timestamp", timestamp);
		System.out.println(uuid);
		System.out.println(timestamp);
		
		//String url = "https://m.yaoee.com/shop/detail/5tnoeovha6iikrcqija87b8om8";
		StringBuffer url = request.getRequestURL();
		
		String signature = DigestUtils.shaHex("jsapi_ticket=HoagFKDcsGMVCIY2vOjf9sTpVMbQ4kfaVd_UnjEDtjwigChMu98aZl9_929NXnSvMgotewtwj5vXYyb3mciIVg&noncestr="+uuid+"&timestamp="+timestamp+"&url="+url);
		model.addAttribute("signature", signature);
		System.out.println(signature);
		return "/iframeTest2";
	}
}