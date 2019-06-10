package com.yang.test.java.jackson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class ObjectToJson {

	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();

		List<String> l = new ArrayList<String>();
		l.add("1");
		l.add("2");
		l.add("3");
		l.add("4");

		String grades = mapper.writeValueAsString(l);
		System.out.println(grades);
	}
}