package com.yang.test.istio.servicea;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class ServiceA {

	public static void main(String[] args) throws IOException, InterruptedException {
		SpringApplication.run(ServiceA.class, args);
	}
}

@RestController
class TestController {

	@RequestMapping("/")
	public String index() throws Exception {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet request = new HttpGet("http://serviceb:8080/");
		HttpResponse response = httpClient.execute(request);
		HttpEntity httpEntity = response.getEntity();
		String result = EntityUtils.toString(httpEntity);
		System.out.println(result);
		return "successa，serviceb：" + result + "，httpcode：" + response.getStatusLine().getStatusCode();
	}
}