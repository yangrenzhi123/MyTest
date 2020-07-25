package com.yang.test.java.elasticsearch;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

@SuppressWarnings("deprecation")
public class EsInsert {

	public static void main(String[] args) throws IOException {
		RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("192.168.8.70", 9200, "http")));

		BulkRequest bulkRequest = new BulkRequest();
		IndexRequest request = null;
		String jsonString = null;

		request = new IndexRequest("test", "h_recycle_record", "a");
		jsonString = "{\"test\":2}";
		request.source(jsonString, XContentType.JSON);
		//client.index(request, RequestOptions.DEFAULT);
		bulkRequest.add(request);

		request = new IndexRequest("test", "h_recycle_record", "gg");
		jsonString = "{\"recyclerecordid\": \"0006016d1050486ca72ca63c6c862111\", \"tenantid\": \"48c5c9209ab448e68363ee5034550dfa\", \"productid\": \"58fd4d48cbe5430c8cca8b5b39469bd5\", \"orderid\": \"84fd945fd13e4147a91ff9d965dc795a\", \"regionid\": \"e313d024cf544342a01436ca1fd667b5\", \"communityid\": \"d1ac49167d1e42caaf1b1ba036216fab\", \"yzqyid\": \"e313d024cf544342a01436ca1fd667b5\", \"yxxqid\": \"d1ac49167d1e42caaf1b1ba036216fab\", \"cjlx\": \"1\", \"sbbh\": \"00012181105394\", \"equipmentid\": \"9b33784d2058473789ae7889d2ced351\", \"tfbz\": \"LYZH190406458401\", \"yhlx\": \"0\", \"tenantgroupid\": \"80fc689225be4e6480d209c68000bb98\", \"operateuserid\": \"\", \"ljlx\": \"03\", \"hsms\": \"1\", \"tdqzl\": \"0\", \"tdhzl\": \"0\", \"tdzl\": \"100\", \"yszl\": \"0\", \"tddj\": \"0.000000\", \"hdjf\": \"1111\", \"sdjf\": \"0\", \"tffs\": \"1\", \"jlly\": \"1\", \"sjly\": \"可回收箱\", \"dxfssl\": \"0\", \"tfsj\": \"20200305 10:39:58\", \"growth_value\": \"0\", \"growth_value_singleprice\": \"0.000000\", \"createuser\": \"171102\", \"createtime\": \"20200305 10:39:57.0\", \"active\": \"0\", \"qy_projectid\": \"\", \"etl\": \"1\", \"gdspid\": \"\", \"jljf\": \"\", \"sfbl\": \"0\"}";
		request.source(jsonString, XContentType.JSON);
		//client.index(request, RequestOptions.DEFAULT);
		bulkRequest.add(request);
		
		client.bulk(bulkRequest, RequestOptions.DEFAULT);
		client.close();
	}
}