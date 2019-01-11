package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
@ConfigurationProperties
public class Config {

	private List<String> dbList = new ArrayList<>();
	private String kafka;

	public List<String> getDbList() {
		return dbList;
	}

	public void setDbList(List<String> dbList) {
		this.dbList = dbList;
	}
	public String getKafka() {
		return kafka;
	}
	public void setKafka(String kafka) {
		this.kafka = kafka;
	}
}