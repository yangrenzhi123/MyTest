package com.yang.test.java.HttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

public class Test3 {

	public static double last;

	public static void main(String[] args) throws ClientProtocolException, IOException, InterruptedException {
		while (true) {
			try {
				Test3.doo();
			}catch(Exception e) {
				e.printStackTrace();
			}
			Thread.sleep(5000);
		}
	}

	public static void doo() throws ClientProtocolException, IOException {
		double begin = 8.92;
		
		HttpGet get = new HttpGet("http://hq.sinajs.cn/list=sz000856");

		HttpClient hc = HttpClients.createDefault();

		HttpResponse response = hc.execute(get);
		HttpEntity httpEntity = response.getEntity();
		String result = EntityUtils.toString(httpEntity);

		String[] ss = result.split("\"");
		String s = ss[1];
		String[] sss = s.split(",");
		int length = sss.length;
		

		String newer = sss[length - 12];
		System.out.println(sss[length - 3] + " " + sss[length - 2]);
		System.out.println(sss[length - 4] + " " + Integer.parseInt(sss[length - 5]) / 100);
		System.out.println(sss[length - 6] + " " + Integer.parseInt(sss[length - 7]) / 100);
		System.out.println(sss[length - 8] + " " + Integer.parseInt(sss[length - 9]) / 100);
		System.out.println(sss[length - 10] + " " + Integer.parseInt(sss[length - 11]) / 100);
		System.out.println(newer + " " + Integer.parseInt(sss[length - 13]) / 100);
		
		System.out.println("");

		System.out.println(sss[length - 22] + " " + Integer.parseInt(sss[length - 23]) / 100);
		System.out.println(sss[length - 20] + " " + Integer.parseInt(sss[length - 21]) / 100);
		System.out.println(sss[length - 18] + " " + Integer.parseInt(sss[length - 19]) / 100);
		System.out.println(sss[length - 16] + " " + Integer.parseInt(sss[length - 17]) / 100);
		System.out.println(sss[length - 14] + " " + Integer.parseInt(sss[length - 15]) / 100);

		double newerDouble = Double.parseDouble(newer);
		if (last == 0) {
			last = newerDouble;
		} else {
			double persent = newerDouble / last;
			System.out.println(persent + "，" + (newerDouble / begin));
			
			if(persent >= 1.005) {
				TestDingding.test("增速：" + persent + "，" + (newerDouble / begin));
			}
			last = newerDouble;
		}
	}
}

class TestDingding {
	private static Logger logger = LoggerFactory.getLogger(TestDingding.class);

	public static String sendPostByMap(String url, Map<String, Object> mapParam) {
		Map<String, String> headParam = new HashMap<>();
		headParam.put("Content-type", "application/json;charset=UTF-8");
		return sendPost(url, mapParam, headParam);
	}

	public static String sendPost(String url, Map<String, Object> param, Map<String, String> headParam) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			URLConnection conn = realUrl.openConnection();
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Fiddler");

			if (headParam != null) {
				for (Entry<String, String> entry : headParam.entrySet()) {
					conn.setRequestProperty(entry.getKey(), entry.getValue());
				}
			}
			conn.setDoOutput(true);
			conn.setDoInput(true);
			out = new PrintWriter(conn.getOutputStream());
			out.print(JSON.toJSONString(param));
			out.flush();
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			logger.error("发送 POST 请求出现异常！", e);
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	public static void test(String content) {
		String dingDingToken = "https://oapi.dingtalk.com/robot/send?access_token=ad88aa82594ae1d596d07282c54eb08a59134e793ce8e49827c76901e19a0a0f";

		Map<String, Object> text = new HashMap<>();
		text.put("content", content);

		Map<String, Object> json = new HashMap<>();
		json.put("msgtype", "text");
		json.put("text", text);

		String response = TestDingding.sendPostByMap(dingDingToken, json);
		System.out.println("相应结果：" + response);
	}
}