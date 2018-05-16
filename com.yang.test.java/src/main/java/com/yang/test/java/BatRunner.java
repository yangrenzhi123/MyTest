package com.yang.test.java;

import java.io.IOException;

public class BatRunner {
	public static void main(String[] args) throws IOException, InterruptedException {
		way7();
	}

	public static void way7() throws IOException{
		Runtime.getRuntime().exec("ping mail.163.com");
	}
}