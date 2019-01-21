package com.example.demo;

import java.io.IOException;
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
public class MonitorForService {

	@Autowired
	Config config;

	public void execute() throws ClassNotFoundException, SQLException, IOException {
		List<String> gws = config.getGw();
		for(String gw : gws) {
			common(gw, "后端网关");
		}
		

		List<String> uiConsoles = config.getUiConsole();
		for(String uiConsole : uiConsoles) {
			common(uiConsole, "运维端");
		}
		

		List<String> uiPlatforms = config.getUiPlatform();
		for(String uiPlatform : uiPlatforms) {
			common(uiPlatform, "租户端");
		}
	}
	
	private void common(String gw, String name) {
		HttpGet get = new HttpGet("http://" + gw);

		CloseableHttpClient httpClient = HttpClients.createDefault();

		MoniResult result = new MoniResult();
		result.setName(name);
		result.setCheckTime(new Date());

		HttpResponse response = null;
		try {
			response = httpClient.execute(get);
		} catch (Exception e) {
			result.setResult(0);
			DemoApplication.result.put(gw, result);
			try {
				httpClient.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			return;
		}

		int code = response.getStatusLine().getStatusCode();

		if (code == 200) {
			result.setResult(1);
			DemoApplication.result.put(gw, result);
		} else {
			result.setResult(0);
			DemoApplication.result.put(gw, result);
		}
		

		try {
			httpClient.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}