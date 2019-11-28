package com.yang.test.java.hive;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * JDBC 操作 Hive（注：JDBC 访问 Hive 前需要先启动HiveServer2）
 */
public class HiveJDBC {

    private static String driverName = "org.apache.hive.jdbc.HiveDriver";
    private static String url = "jdbc:hive2://192.168.10.90:10000/default";
    private static String user = "root";
    private static String password = "";
    private static Connection conn = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName(driverName);
		conn = DriverManager.getConnection(url, user, password);
		stmt = conn.createStatement();
		
		String sql = null;
		

        /*sql = "drop table if exists h_recycle_record";
        System.out.println("Running: " + sql);
        stmt.execute(sql);
        

        sql = "create table h_recycle_record(recyclerecordid String,tenantid String,productid String,orderid String,regionid String,communityid String,yzqyid String,yxxqid String,cjlx String,sbbh String,equipmentid String,tfbz String,yhlx String,tenantgroupid String,operateuserid String,ljlx String,hsms String,tdqzl String,tdhzl String,tdzl String,yszl String,tddj String,hdjf String,sdjf String,tffs String,jlly String,sjly String,dxfssl String,tfsj String,growth_value String,growth_value_singleprice String,createuser String,createtime String,active String,recyclerecordzzid bigint,qy_projectid String,etl String,gdspid String) row format delimited fields terminated by '\t'";
        System.out.println("Running: " + sql);
		stmt.execute(sql);*/
		

        /*sql = "desc h_recycle_record";
        System.out.println("Running: " + sql);
        rs = stmt.executeQuery(sql);
        while (rs.next()) {
            System.out.println(rs.getString(1) + "\t" + rs.getString(2));
        }*/

		
		/*String filePath = "/home/h_recycle_record_100000001_186196962";
		sql = "load data local inpath '" + filePath + "' into table h_recycle_record";
		//sql = "load data local inpath '" + filePath + "' overwrite into table h_recycle_record";
		System.out.println("Running: " + sql);
		long a = System.currentTimeMillis();
		stmt.execute(sql);
		System.out.println("数据导入耗时：" + (System.currentTimeMillis() - a));*/
        
		/*sql = "insert into table h_recycle_record select * from h_recycle_record";
		System.out.println("Running: " + sql);
		long a = System.currentTimeMillis();
		stmt.execute(sql);
		System.out.println("数据insert into耗时：" + (System.currentTimeMillis() - a));*/
		

        sql = "select * from h_recycle_record limit 10";
        System.out.println("Running: " + sql);
        rs = stmt.executeQuery(sql);
        System.out.println("ID" + "\t" + "收集类型" + "\t" + "投放时间");
        while (rs.next()) {
            System.out.println(rs.getString("recyclerecordid") + "\t" + rs.getString("sjly") + "\t" + rs.getString("tfsj"));
//            System.out.println(rs.getString(1) + "\t" + rs.getString(2));
        }
        

		if (rs != null) {
			rs.close();
		}
		if (stmt != null) {
			stmt.close();
		}
		if (conn != null) {
			conn.close();
		}
	}

    // 创建数据库
    //@Test
    public void createDatabase() throws Exception {
        String sql = "create database hive_jdbc_test";
        System.out.println("Running: " + sql);
        stmt.execute(sql);
    }

    // 查询所有数据库
    //@Test
    public void showDatabases() throws Exception {
        String sql = "show databases";
        System.out.println("Running: " + sql);
        rs = stmt.executeQuery(sql);
        while (rs.next()) {
            System.out.println(rs.getString(1));
        }
    }

    // 查询所有表
    //@Test
    public void showTables() throws Exception {
        String sql = "show tables";
        System.out.println("Running: " + sql);
        rs = stmt.executeQuery(sql);
        while (rs.next()) {
            System.out.println(rs.getString(1));
        }
    }

    // 统计查询（会运行mapreduce作业）
    //@Test
    public void countData() throws Exception {
        String sql = "select count(1) from emp";
        System.out.println("Running: " + sql);
        rs = stmt.executeQuery(sql);
        while (rs.next()) {
            System.out.println(rs.getInt(1) );
        }
    }

    // 删除数据库
    //@Test
    public void dropDatabase() throws Exception {
        String sql = "drop database if exists hive_jdbc_test";
        System.out.println("Running: " + sql);
        stmt.execute(sql);
    }
}