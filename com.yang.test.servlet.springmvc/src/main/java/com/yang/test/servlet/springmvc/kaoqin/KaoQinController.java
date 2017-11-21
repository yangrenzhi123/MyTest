package com.yang.test.servlet.springmvc.kaoqin;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@SuppressWarnings({ "unused" })
@Controller
public class KaoQinController {

	@RequestMapping(value = "/kaoqin/accept", method = RequestMethod.POST)
	@ResponseBody
	public KaoQinReponse accept(String data, String version) throws JsonParseException, JsonMappingException, IOException {
		data = "{\"data\": " + data + "}";

		ObjectMapper mapper = new ObjectMapper();
		KaoQinDto KaoQinDto = mapper.readValue(data, KaoQinDto.class);

		KaoQinData d = KaoQinDto.getData().get(0);
		KaoQinReponse result = new KaoQinReponse();
		result.setStatus(100);
		result.setStatusMessage("成功");
		return result;
	}
}