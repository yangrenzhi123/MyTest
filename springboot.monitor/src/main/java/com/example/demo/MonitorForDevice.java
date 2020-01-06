package com.example.demo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MonitorForDevice {

	@Autowired
	Config config;

	public void execute() throws ClassNotFoundException, SQLException, IOException {
		List<String> canals = config.getCanal();
		for(String canal : canals) {
			String[] ss = canal.split(":");
			common(ss[0], Integer.parseInt(ss[1]), "Canal.Server");
		}
		List<String> gws = config.getDevice();
		for(String gw : gws) {
			String[] ss = gw.split(":");
			common(ss[0], Integer.parseInt(ss[1]), "箱体服务");
		}

		String mongodb = config.getMongodb();
		String[] ss = mongodb.split(":");
		common(ss[0], Integer.parseInt(ss[1]), "MongoDB");
	}
	
	private void common(String ip, int port, String name) {
		MoniResult result = new MoniResult();
		result.setName(name);
		result.setCheckTime(new Date());
		try {
			
			Socket socket = new Socket();
			socket.connect(new InetSocketAddress(ip, port), 3000);
			socket.close();
			

			result.setResult(1);
			MonitorStartup.result.put(ip+":"+port, result);
		} catch (Exception e) {
			result.setResult(0);
			MonitorStartup.result.put(ip+":"+port, result);

			DateFormat yyyy = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			TestDingding.test("检测时间：" + yyyy.format(result.getCheckTime()) + "，结果：" + (result.getResult() == 1 ? "成功" : "<span style='color:red'>失败</span>") + "，" + result.getName() + "，备注：" + result.getName());
			return;
		}
	}
}