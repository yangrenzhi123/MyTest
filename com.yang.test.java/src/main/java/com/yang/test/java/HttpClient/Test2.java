package com.yang.test.java.HttpClient;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class Test2 {

	public static void main(String[] args) throws ClientProtocolException, IOException {
		HttpGet get = new HttpGet("http://hq.sinajs.cn/list=sh600550");

		HttpClient hc = HttpClients.createDefault();

		HttpResponse response = hc.execute(get);
		HttpEntity httpEntity = response.getEntity();
		String result = EntityUtils.toString(httpEntity);

		String[] ss = result.split("\"");
		String s = ss[1];
		String[] sss = s.split(",");
		int length = sss.length;
		System.out.println(sss[length - 3] + " " + sss[length - 2]);
		System.out.println(sss[length - 4] + " " + sss[length - 5]);
		System.out.println(sss[length - 6] + " " + sss[length - 7]);
		System.out.println(sss[length - 8] + " " + sss[length - 9]);
		System.out.println(sss[length - 10] + " " + sss[length - 11]);
		System.out.println(sss[length - 12] + " " + sss[length - 13]);
		System.out.println("");
		System.out.println(sss[length - 22] + " " + sss[length - 23]);
		System.out.println(sss[length - 20] + " " + sss[length - 21]);
		System.out.println(sss[length - 18] + " " + sss[length - 19]);
		System.out.println(sss[length - 16] + " " + sss[length - 17]);
		System.out.println(sss[length - 14] + " " + sss[length - 15]);
	}
}