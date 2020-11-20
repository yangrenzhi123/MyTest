package com.example.demo;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yang.test.java.springboot.monitor.model.DingDingMessageModel;

@Service
public class MonitorForService {

	@Autowired
	Config config;

	public void execute() throws ClassNotFoundException, SQLException, IOException {
		DateFormat yyyy = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		List<String> consoleList = config.getConsole();
		if (consoleList != null) {
			for (String console : consoleList) {
				common(console, "console_JAVA服务");
			}
		}

		List<String> canalClientList = config.getCanalClient();
		if (canalClientList != null) {
			for (String canalClient : canalClientList) {
				common(canalClient, "canal_JAVA服务");
			}
		}

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
		List<DingDingMessageModel> messages = new ArrayList<>();
		if(daos != null && daos.size() > 0) {
			for (String dao : daos) {
				int code = common2(dao);
				if(code != 200 && code != 404) {
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
					}
					code = common2(dao);
				}

				MoniResult result = new MoniResult();
				result.setName("DAO服务");
				result.setCheckTime(new Date());
				if(code != 200 && code != 404) {
					result.setResult(0);
					MonitorStartup.result.put(dao, result);

					DingDingMessageModel message = new DingDingMessageModel();
					message.setTarget(result.getName());
					message.setIp(dao);
					message.setHttpCode(code);
					messages.add(message);
				}else {
					result.setResult(1);
					MonitorStartup.result.put(dao, result);
				}
			}
		}
		if(messages.size() > 0) {
			StringBuilder sb = new StringBuilder();
			sb.append("时间：");sb.append(yyyy.format(new Date()));
			sb.append("\r");
			sb.append("服务：com-lyzh-msa-dao-service，服务数量：");sb.append(daos.size());sb.append("个，异常数量：");sb.append(messages.size());sb.append("个。");
			sb.append("\r");
			sb.append("详情如下：");
			sb.append("\r");
			for(DingDingMessageModel message : messages) {
				sb.append("HttpCode：");sb.append(message.getHttpCode());sb.append("，信息：");sb.append(message.getIp());
				sb.append("\r");
			}
			sb.append("本次异常将于5分钟后再次检测，若异常已修复，该警告不再提醒。");
			sb.append("\r");
			sb.append("\r");
			sb.append("@蔡伟平");
			TestDingding.test(config.getDingDingToken(), sb.toString());
		}

		List<String> gws = config.getGw();
		for (String gw : gws) {
			common(gw, "后端网关");
		}

		List<String> uiConsoles = config.getUiConsole();
		for (String uiConsole : uiConsoles) {
			common(uiConsole, "运维端");
		}

		List<String> etls = config.getEtl();
		for (String etl : etls) {
			common(etl, "ETL");
		}

		List<String> css = config.getCs();
		for (String cs : css) {
			common(cs, "CS");
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

	
	private int common2(String url) {
		int timeout = 5000;
		RequestConfig rc = RequestConfig.custom().setSocketTimeout(timeout).setConnectTimeout(timeout).setConnectionRequestTimeout(timeout).build();
		
		HttpGet get = new HttpGet("http://" + url);
		get.setConfig(rc);
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		HttpResponse response = null;
		int code;
		try {
			response = httpClient.execute(get);
		} catch (IOException e) {
			code = 500;
			return code;
		} catch (Exception e) {
			code = 500;
			return code;
		} finally {
			try {
				httpClient.close();
			} catch (IOException e) {
				code = 500;
				return code;
			}
		}
		code = response.getStatusLine().getStatusCode();
		return code;
	}
	
	private void common(String url, String name) throws IOException {
		int timeout = 5000;
		RequestConfig rc = RequestConfig.custom().setSocketTimeout(timeout).setConnectTimeout(timeout).setConnectionRequestTimeout(timeout).build();
		
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
			TestDingding.test(config.getDingDingToken(), yyyy.format(result.getCheckTime()) + "，检测到 " + result.getName()  + " 发生异常，将于5分钟后再次检测。若异常已修复，该警告不再提醒。");
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
			TestDingding.test(config.getDingDingToken(), yyyy.format(result.getCheckTime()) + "，检测到 " + result.getName()  + " 发生异常，将于5分钟后再次检测。若异常已修复，该警告不再提醒。");
		}


		httpClient.close();
	}
}