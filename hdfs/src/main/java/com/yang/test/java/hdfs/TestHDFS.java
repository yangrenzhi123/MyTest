package com.yang.test.java.hdfs;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.RemoteIterator;

public class TestHDFS {

	
	/*/opt/modules/app/hadoop-2.6.5/data/tmp/dfs/data/current/BP-2097822308-192.168.18.231-1573805007663/current/finalized/subdir0/subdir0*/
	public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException {
		Configuration conf = new Configuration();
		FileSystem fs = FileSystem.get(new URI("hdfs://172.17.19.38:8020"), conf, "root");

//		fs.copyFromLocalFile(false, true, new Path("C:/test.xml"), new Path("/demo1/test.xml"));
		fs.copyToLocalFile(false, new Path("/output2/part-00000"), new Path("C:/1.txt"), true);
		

		//fs.copyFromLocalFile(false, true, new Path("C:/1.txt"), new Path("/output2/1.txt"));
		
//		RemoteIterator<LocatedFileStatus> l = fs.listFiles(new Path("/output2"), true);
//		while(l.hasNext()) {
//			LocatedFileStatus i = l.next();
//			System.out.println(i.toString());
//		}
		
//		fs.delete(new Path("/output2"), true);
		fs.close();
	}
}