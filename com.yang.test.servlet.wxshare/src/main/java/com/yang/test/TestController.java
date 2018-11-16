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
		
		String signature = DigestUtils.shaHex("jsapi_ticket=HoagFKDcsGMVCIY2vOjf9sTpVMbQ4kfaVd_UnjEDtjwHpqYLOU9YxUfNqrSnSjNghVsf3062B0utFDNt90NkJA&noncestr="+uuid+"&timestamp="+timestamp+"&url="+request.getRequestURL());
		model.addAttribute("signature", signature);
		return "/iframeTest2";
	}
}