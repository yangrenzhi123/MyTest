package com.yang.test.java.spring.cloud.feign;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yang.test.java.spring.cloud.feign.rpc.NounClient;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class FeignHttpclientStartup {

	public static void main(String[] args) {
		SpringApplication.run(FeignHttpclientStartup.class, args);
	}
}

@RestController
class HelloController {

	@Autowired
	NounClient sender;

	@GetMapping("/")
	public String hello2() {
		return sender.getWord();
	}

	@Bean
	public PoolingHttpClientConnectionManager poolingHttpClientConnectionManager() {
		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
		cm.setMaxTotal(400);
		cm.setDefaultMaxPerRoute(200);
		return cm;
	}

	@Bean
	public HttpClientBuilder httpClientBuilder(HttpClientConnectionManager connManager) {
		RequestConfig config = RequestConfig.custom().setConnectTimeout(3000).setSocketTimeout(1500).setConnectionRequestTimeout(1500).build();
		HttpClientBuilder builder = HttpClientBuilder.create();
		builder.setConnectionManager(connManager);
		builder.setDefaultRequestConfig(config);
		builder.setRetryHandler(new DefaultHttpRequestRetryHandler(0, false));
		return builder;
	}
}