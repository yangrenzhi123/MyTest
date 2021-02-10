package com.yang.test.java.spi;

import java.util.HashMap;
import java.util.ServiceLoader;

public class Test {

	public static void main(String[] args) {
		HashMap<String, String> m = new HashMap<>();
		m.put("1", "1");
		
		System.out.println(10000000 >>> 16);
		
		
		ServiceLoader<UploadCDN> uploadCDN = ServiceLoader.load(UploadCDN.class);
		for (UploadCDN u : uploadCDN) {
			u.upload("filePath");
		}
	}
}