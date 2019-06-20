package com.yang.test.java.hash;

import java.util.zip.CRC32;

public class CRCTest {

	public static void main(String[] args) {
		CRC32 crc = new CRC32();
		crc.update("123".getBytes());
		long a = crc.getValue();
		System.out.println(a);
	}
}