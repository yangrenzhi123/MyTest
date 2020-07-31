package com.yang.test.java.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadLine {

	public static void main(String[] args) throws IOException {
		FileReader fr = new FileReader("C:/1.txt");
		BufferedReader bf = new BufferedReader(fr);
		String str; // 按行读取字符串
		while ((str = bf.readLine()) != null) {
			System.out.println(str);
		}

		bf.close();
	}
}