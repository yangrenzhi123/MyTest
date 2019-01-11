package com.example.demo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MonitorForDevice {

	@Autowired
	Config config;

	public void execute() throws ClassNotFoundException, SQLException, IOException {
		List<String> gws = config.getDevice();
		for(String gw : gws) {
			String[] ss = gw.split(":");
			common(ss[0], Integer.parseInt(ss[1]), "箱体服务");
		}
		

		List<String> uiConsoles = config.getThird();
		for(String uiConsole : uiConsoles) {
			String[] ss = uiConsole.split(":");
			common(ss[0], Integer.parseInt(ss[1]), "发放机服务");
		}
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
			DemoApplication.result.put(ip+":"+port, result);
		} catch (Exception e) {
			result.setResult(0);
			DemoApplication.result.put(ip+":"+port, result);
			return;
		}
	}
}