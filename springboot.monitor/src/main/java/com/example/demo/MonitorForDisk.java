package com.example.demo;

import java.io.IOException;
import java.sql.SQLException;
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
			return;
		}

		int code = response.getStatusLine().getStatusCode();

		if (code == 200) {
			result.setResult(1);
			MonitorStartup.result.put(disk, result);
		} else {
			result.setResult(0);
			MonitorStartup.result.put(disk, result);
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