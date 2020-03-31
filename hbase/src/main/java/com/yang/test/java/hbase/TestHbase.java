package com.yang.test.java.hbase;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;

public class TestHbase {
	public static void main(String[] args) throws IOException {
		Configuration configuration = HBaseConfiguration.create();
		configuration.set("hbase.zookeeper.property.clientPort", "2181");
		configuration.set("hbase.zookeeper.quorum", "172.18.28.142");

		Connection connection = ConnectionFactory.createConnection(configuration);
		System.out.println(connection);

		Admin admin = connection.getAdmin();
		if (admin.tableExists(TableName.valueOf("test"))) {
			System.out.println("表已存在！");
		}else {
			System.out.println("表不已存在！");
		}
	}
}