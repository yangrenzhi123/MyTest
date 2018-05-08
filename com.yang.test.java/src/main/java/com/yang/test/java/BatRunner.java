package com.yang.test.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

public class BatRunner {
	public static void main(String[] args) throws IOException, InterruptedException {
		way7();
	}

	public static void way7() throws IOException{
		Process p = Runtime.getRuntime().exec("ping mail.163.com");
		console(p);
	}

	private static void console(Process p) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream(), "GBK"));
		String line;
		while ((line = br.readLine()) != null) {
			System.out.println(line);
		}
		br.close();
	}
	
	private static void way6() throws IOException{
		Process p = Runtime.getRuntime().exec(
				"cmd.exe /c xcopy /s/i C:\\virgo-tomcat-server-3.0.3.RELEASE4\\pickup\\com.qs*.war C:\\virgo-tomcat-server-3.0.3.RELEASE4\\pickup"+new Date().toLocaleString().replaceAll(":", "_").replaceAll(" ", ""));
		BufferedReader br = new BufferedReader(new InputStreamReader(
				p.getInputStream(), "GBK"));
		String line;
		while ((line = br.readLine()) != null) {
			System.out.println(line);
		}
		br.close();
	}
	
	private static void way5() throws IOException, InterruptedException {
		Process p = Runtime.getRuntime().exec(
				"cmd.exe /c xcopy /s/i C:\\1\\* D:\\"+ new Date().toLocaleString().replaceAll(":", "_").replaceAll(" ", ""));
		BufferedReader br = new BufferedReader(new InputStreamReader(
				p.getInputStream(), "GBK"));
		String line;
		while ((line = br.readLine()) != null) {
			System.out.println(line);
		}
		br.close();
	}

	private static void way4() throws IOException, InterruptedException {
		Runtime.getRuntime()
				.exec("cmd.exe /c start C:/virgo-tomcat-server-3.0.3.RELEASE1/bin/startup.bat -clean");
	}

	private static void way3() throws IOException {

		Process p = Runtime.getRuntime().exec("D:/deployTools/backUp.bat");
		BufferedReader br = new BufferedReader(new InputStreamReader(
				p.getInputStream(), "GBK"));
		String line;
		while ((line = br.readLine()) != null) {
			System.out.println(line);
		}
		br.close();
	}

	private static void way2() throws IOException, InterruptedException {
		Runtime r = Runtime.getRuntime();

		Process p2 = Runtime
				.getRuntime()
				.exec("cmd.exe /c C:/virgo-tomcat-server-3.0.3.RELEASE1/bin/shutdown.bat -clean");

		// Thread.currentThread().wait(5000);
		Process p = Runtime
				.getRuntime()
				.exec("cmd.exe /c C:/virgo-tomcat-server-3.0.3.RELEASE1/bin/startup.bat -clean");

		BufferedReader br = new BufferedReader(new InputStreamReader(
				p.getInputStream(), "GBK"));
		String line;
		while ((line = br.readLine()) != null) {
			System.out.println(line);
		}
		br.close();
	}

	private void way1() throws IOException {
		String command = "D:/projects/yiwutong/com.qs.aggregator/deploy/安装jarAndWar到usr.bat";
		Process p = Runtime.getRuntime().exec(command);
		BufferedReader br = new BufferedReader(new InputStreamReader(
				p.getInputStream(), "GBK"));
		String line;
		while ((line = br.readLine()) != null) {
			System.out.println("StartedLog==>" + line);
		}
		br.close();
	}
}