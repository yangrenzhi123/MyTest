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

	public static void doo() throws ClientProtocolException, IOException {
		HttpGet get = new HttpGet("http://hq.sinajs.cn/list=sh600550");

		HttpClient hc = HttpClients.createDefault();

		HttpResponse response = hc.execute(get);
		HttpEntity httpEntity = response.getEntity();
		String result = EntityUtils.toString(httpEntity);

		String[] ss = result.split("\"");
		String s = ss[1];
		String[] sss = s.split(",");
		int length = sss.length;
		System.out.println(sss[length -  3] + " " + sss[length - 2]);
		System.out.println(sss[length -  4] + " " + Integer.parseInt(sss[length -  5])/100);
		System.out.println(sss[length -  6] + " " + Integer.parseInt(sss[length -  7])/100);
		System.out.println(sss[length -  8] + " " + Integer.parseInt(sss[length -  9])/100);
		System.out.println(sss[length - 10] + " " + Integer.parseInt(sss[length - 11])/100);
		System.out.println(sss[length - 12] + " " + Integer.parseInt(sss[length - 13])/100);
		System.out.println("");
		System.out.println(sss[length - 22] + " " + Integer.parseInt(sss[length - 23])/100);
		System.out.println(sss[length - 20] + " " + Integer.parseInt(sss[length - 21])/100);
		System.out.println(sss[length - 18] + " " + Integer.parseInt(sss[length - 19])/100);
		System.out.println(sss[length - 16] + " " + Integer.parseInt(sss[length - 17])/100);
		System.out.println(sss[length - 14] + " " + Integer.parseInt(sss[length - 15])/100);
	}
}