package com.example.demo;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
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
		List<String> platformList = config.getPlatform();
		if(platformList != null) {
			for (String platform : platformList) {
				common(platform, "Platform服务");
			}
		}
		
		List<String> schedulerList = config.getScheduler();
		if(schedulerList != null) {
			for (String scheduler : schedulerList) {
				common(scheduler, "Scheduler服务");
			}
		}

		List<String> nmss = config.getNms();
		for (String nms : nmss) {
			common(nms, "NMS服务");
		}

		List<String> files = config.getFile();
		for (String file : files) {
			common(file, "FILE服务");
		}

		List<String> fls = config.getFl();
		for (String fl : fls) {
			common(fl, "FL服务");
		}

		List<String> dcs = config.getDc();
		for (String dc : dcs) {
			common(dc, "DC服务");
		}

		List<String> cas = config.getCa();
		for (String ca : cas) {
			common(ca, "CA服务");
		}

		List<String> daos = config.getDao();
		for (String dao : daos) {
			common(dao, "DAO服务");
		}

		List<String> gws = config.getGw();
		for (String gw : gws) {
			common(gw, "后端网关");
		}

		List<String> uiConsoles = config.getUiConsole();
		for (String uiConsole : uiConsoles) {
			common(uiConsole, "运维端");
		}

		List<String> uiPlatforms = config.getUiPlatform();
		for (String uiPlatform : uiPlatforms) {
			common(uiPlatform, "租户端");
		}

		List<String> thirds = config.getThird();
		for (String third : thirds) {
			common(third, "发放机服务");
		}
	}

	private void common(String url, String name) throws IOException {
		int timeout = 5000;
		RequestConfig rc = RequestConfig.custom().setSocketTimeout(timeout).setConnectTimeout(5000).setConnectionRequestTimeout(5000).build();
		
		HttpGet get = new HttpGet("http://" + url);
		get.setConfig(rc);

		CloseableHttpClient httpClient = HttpClients.createDefault();

		MoniResult result = new MoniResult();
		result.setName(name);
		result.setCheckTime(new Date());

		HttpResponse response = null;
		try {
			response = httpClient.execute(get);
		} catch (Exception e) {
			result.setResult(0);
			MonitorStartup.result.put(url, result);
			try {
				httpClient.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			DateFormat yyyy = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			TestDingding.test("检测时间：" + yyyy.format(result.getCheckTime()) + "，结果：" + (result.getResult() == 1 ? "成功" : "<span style='color:red'>失败</span>") + "，" + result.getName() + "，备注：" + result.getName());
			return;
		}

		int code = response.getStatusLine().getStatusCode();

		if (code == 200) {
			result.setResult(1);
			MonitorStartup.result.put(url, result);
		} else if(code == 404){
			result.setResult(1);
			MonitorStartup.result.put(url, result);
		} else {
			result.setResult(0);
			MonitorStartup.result.put(url, result);

			DateFormat yyyy = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			TestDingding.test("检测时间：" + yyyy.format(result.getCheckTime()) + "，结果：" + (result.getResult() == 1 ? "成功" : "<span style='color:red'>失败</span>") + "，" + result.getName() + "，备注：" + result.getName());
		}


		httpClient.close();
	}
}