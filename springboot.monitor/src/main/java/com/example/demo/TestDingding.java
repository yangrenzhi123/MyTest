package com.example.demo;
import com.alibaba.fastjson.JSON;
import com.yang.test.java.springboot.monitor.model.DingDingMessageModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class TestDingding {
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

	public static void test(String dingDingToken, String content) {
		//String dingDingToken = "https://oapi.dingtalk.com/robot/send?access_token=e0579969cafea588e71a3ee9175bd9b508895a3847b0a3dc23be615e06ef2755";

		Map<String, Object> text = new HashMap<>();
		text.put("content", content);

		Map<String, Object> json = new HashMap<>();
		json.put("msgtype", "text");
		json.put("text", text);

		String response = TestDingding.sendPostByMap(dingDingToken, json);
		System.out.println("相应结果：" + response);
	}
	
	public static void main(String[] args) {
		DateFormat yyyy = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		List<DingDingMessageModel> messages = new ArrayList<>();
		DingDingMessageModel message1 = new DingDingMessageModel();
		message1.setHttpCode(500);
		message1.setIp("这是一条测试数据");
		messages.add(message1);
		DingDingMessageModel message2 = new DingDingMessageModel();
		message2.setHttpCode(500);
		message2.setIp("这是一条测试数据");
		messages.add(message2);

		if(messages.size() > 0) {
			StringBuilder sb = new StringBuilder();
			sb.append("时间：");sb.append(yyyy.format(new Date()));
			sb.append("\r");
			sb.append("服务：com-lyzh-msa-dao-service，服务数量：");sb.append(4);sb.append("个，异常数量：");sb.append(messages.size());sb.append("个。");
			sb.append("\r");
			sb.append("详情如下：");
			sb.append("\r");
			for(DingDingMessageModel message : messages) {
				sb.append("HttpCode：");sb.append(message.getHttpCode());sb.append("，信息：");sb.append(message.getIp());
				sb.append("\r");
			}
			sb.append("本次异常将于5分钟后再次检测，若异常已修复，该警告不再提醒。");
			sb.append("\r");
			sb.append("---主从告警---");
			sb.append("\r");
			sb.append("\r");
			sb.append("@蔡伟平");
			TestDingding.test("https://oapi.dingtalk.com/robot/send?access_token=29697797dfeb6231987d550804150409850385ef11a0efbc4794c911277afedb", sb.toString());
		}
	}
}