package com.yang.test.java.influxdb;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.influxdb.dto.QueryResult.Result;
import org.influxdb.dto.QueryResult.Series;

public class InfluxdbClient {

	public static void main(String[] args) {
		InfluxDB db = InfluxDBFactory.connect("http://192.168.8.70:8086", "root", "123456");

//		final Query query = new Query("create database test");
//		QueryResult qr = db.query(query);
//		System.out.println(qr);
		
		db.setDatabase("test");

		db.write(Point.measurement("bbbt").time(System.currentTimeMillis(), TimeUnit.MILLISECONDS).addField("hostname", "server01").addField("value", 442221834240L).build());
		
		//final Query query = new Query("delete from bbbt where hostname='server01'");
		final Query query = new Query("select * from bbbt order by time desc");
		QueryResult qr = db.query(query);
		List<Result> l = qr.getResults();
		if(l != null && l.size() > 0) {
			System.out.println(l);
			for(Result r : l) {
				List<Series> ls = r.getSeries();
				for(Series s : ls) {
					List<List<Object>> gs = s.getValues();
					for(List<Object> g : gs) {
						System.out.println(g);
					}
				}
			}
		}
	}
}