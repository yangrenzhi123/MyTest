package com.yang.test.java.hdfs;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class TestHDFS {

	
	/*/opt/modules/app/hadoop-2.6.5/data/tmp/dfs/data/current/BP-2097822308-192.168.18.231-1573805007663/current/finalized/subdir0/subdir0*/
	public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException {
		Configuration conf = new Configuration();
		FileSystem fs = FileSystem.get(new URI("hdfs://192.168.10.228:8020"), conf, "root");

//		fs.copyFromLocalFile(false, true, new Path("C:/test.xml"), new Path("/demo1/test.xml"));
//		fs.copyToLocalFile(false, new Path("/demo1/test.xml"), new Path("C:/test2.xml"), true);
		

		fs.copyFromLocalFile(false, true, new Path("C:/1.txt"), new Path("/home/1.txt"));
		fs.close();
	}
}