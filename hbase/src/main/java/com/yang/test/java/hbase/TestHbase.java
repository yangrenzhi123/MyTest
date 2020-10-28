package com.yang.test.java.hbase;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

/** 程序运行要配置hosts，172.18.6.98 hbase master */
public class TestHbase {
	public static void main(String[] args) throws IOException {
		Configuration configuration = HBaseConfiguration.create();
		configuration.set("hbase.zookeeper.property.clientPort", "2181");
		configuration.set("hbase.zookeeper.quorum", "192.168.8.70");

		Connection connection = ConnectionFactory.createConnection(configuration);
		System.out.println(connection);

		Admin admin = connection.getAdmin();
		if (admin.tableExists(TableName.valueOf("test"))) {
			System.out.println("表已存在！");
		} else {
			System.out.println("表不已存在！");
		}

//		HTableDescriptor[] as = admin.listTables();
//		for (HTableDescriptor item : as) {
//			System.out.println(item.getNameAsString());
//		}


		TableName tableName = TableName.valueOf("test");
		Table table = connection.getTable(tableName);
		
//		Get get = new Get(Bytes.toBytes(1));
//		System.out.println(table.get(get));

		byte[] v = Bytes.toBytes(3);
		Put put = new Put(v);
		put.addColumn("f1".getBytes(), "name".getBytes(), "张三".getBytes());
		table.put(put);
		
		Scan scan = new Scan();
		scan.setStartRow(Bytes.toBytes(1));
		scan.setStopRow(Bytes.toBytes(3));
		ResultScanner resultScanner = table.getScanner(scan);
		for (Result result : resultScanner) {
			Cell[] cells = result.rawCells();
			for (Cell cell : cells) {
				System.out.println("----------------------------------------");
				System.out.println(Bytes.toString(CellUtil.cloneValue(cell)));
				System.out.println();
			}
		}
		
		//table.get(gets)
		
//		Get get = new Get(v);
//		Result result = table.get(get);
//		Cell[] cells = result.rawCells();
//		for (Cell cell : cells) {
//			System.out.println("----------------------------------------");
//			//System.out.println(Bytes.toString(CellUtil.cloneRow(cell)));
//			//System.out.println(Bytes.toString(CellUtil.cloneFamily(cell)));
//			System.out.println(Bytes.toString(CellUtil.cloneValue(cell)));
//			System.out.println();
//		}
	}
}