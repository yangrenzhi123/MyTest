package com.yang.test.java.hdfs;

import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class TestHDFS {

	public static void main(String[] args) {

		try {
			Configuration conf = new Configuration();
			FileSystem fs = FileSystem.get(new URI("hdfs://192.168.30.151:8020"), conf, "root");

			fs.copyToLocalFile(false, new Path("/demo1/core-site.xml"), new Path("C:/"), true);
			fs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}