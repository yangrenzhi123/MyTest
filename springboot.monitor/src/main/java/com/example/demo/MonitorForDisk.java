package com.example.demo;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MonitorForDisk {

	@Autowired
	Config config;

	public void execute() throws ClassNotFoundException, SQLException, IOException {
		List<String> diskes = config.getDisk();
		for (String disk : diskes) {
			common(disk);
		}
	}

	private void common(String disk) throws ParseException, IOException {
		HttpGet get = new HttpGet("http://" + disk);

		CloseableHttpClient httpClient = HttpClients.createDefault();

		MoniResult result = new MoniResult();
		result.setCheckTime(new Date());

		HttpResponse response = null;
		try {
			response = httpClient.execute(get);
		} catch (Exception e) {
			result.setResult(0);
			MonitorStartup.result.put(disk, result);
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
			MonitorStartup.result.put(disk, result);
		} else {
			result.setResult(0);
			MonitorStartup.result.put(disk, result);

			DateFormat yyyy = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			TestDingding.test("检测时间：" + yyyy.format(result.getCheckTime()) + "，结果：" + (result.getResult() == 1 ? "成功" : "<span style='color:red'>失败</span>") + "，" + result.getName() + "，备注：" + result.getName());
		}

		HttpEntity httpEntity = response.getEntity();
		String persent = EntityUtils.toString(httpEntity);
		result.setName("磁盘最高占用比" + persent + "%");

		try {
			httpClient.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}