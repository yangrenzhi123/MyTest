package com.yang.test.java.httpclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Test5 {

	public static void main(String[] args) throws IOException {
		URL url = new URL("http://172.17.144.93:31143/");
		URLConnection connection = url.openConnection();
		connection.setConnectTimeout(300000);
		connection.setReadTimeout(300000);
		long a = System.currentTimeMillis();
		try {
			InputStream is = connection.getInputStream();// 获取内容的字节流
			InputStreamReader isr = new InputStreamReader(is, "utf-8");// 将字节流包装为字符串流，并制定编码格式为utf-8
			BufferedReader br = new BufferedReader(isr);// 创建一个实例用来存放转换后的字符
			String line;// 定义一个字符串类型变量
			StringBuilder builder = new StringBuilder();// 创建实例
			while ((line = br.readLine()) != null) {// 读取信息，并且当信息不为空时
				builder.append(line + "\n");// append方法使builder包含line中的所有信息
			}
			br.close();// 关闭流
			isr.close();// 关闭流
			is.close();// 关闭流
			System.out.println(builder);// 输出读取的信息
		}catch(Exception e) {
			System.out.println(System.currentTimeMillis() - a);
			e.printStackTrace();
		}
	}
}