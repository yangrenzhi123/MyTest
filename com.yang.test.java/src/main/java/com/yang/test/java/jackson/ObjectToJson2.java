package com.yang.test.java.jackson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class ObjectToJson2 {

	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();

		Map<String, String> m = new HashMap<>();
		m.put("a", "a");
		m.put("b", "b");
		m.put("c", "c");

		String grades = mapper.writeValueAsString(m);
		System.out.println(grades);
		System.out.println(m);
	}
}