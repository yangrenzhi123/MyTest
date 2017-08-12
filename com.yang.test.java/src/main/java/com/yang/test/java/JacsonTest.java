package com.yang.test.java;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class JacsonTest {

	public static void main(String[] args) throws JsonParseException,
			JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);

		String info = "{\"Id\":1, \"test\":1}";

		JacksonTestModel arrn = mapper.readValue(info, JacksonTestModel.class);
		String info2 = mapper.writeValueAsString(arrn);
		System.out.println(info2);
	}
}