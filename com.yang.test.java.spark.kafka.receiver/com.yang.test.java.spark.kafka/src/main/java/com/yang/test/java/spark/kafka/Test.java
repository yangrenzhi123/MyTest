package com.yang.test.java.spark.kafka;

import java.io.File;

public class Test {

	public static void main(String[] args) {
		File directory = new File("C:\\Users\\yrz\\Documents\\GitHub\\MyTest2\\com.yang.test.java.spark.kafka.receiver\\com.yang.test.java.spark.kafka\\target\\dependency");
		
		File [] currentFiles = directory.listFiles();
		for(File f : currentFiles) {
			System.out.print("/home/k/"+f.getName()+",");
		}
	}
}