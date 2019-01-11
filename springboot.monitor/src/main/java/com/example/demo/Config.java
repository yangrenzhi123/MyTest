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
	private String redis;
	private List<String> gw;
	private List<String> uiConsole;
	private List<String> uiPlatform;
	


	public List<String> getUiConsole() {
		return uiConsole;
	}
	public void setUiConsole(List<String> uiConsole) {
		this.uiConsole = uiConsole;
	}
	public List<String> getUiPlatform() {
		return uiPlatform;
	}
	public void setUiPlatform(List<String> uiPlatform) {
		this.uiPlatform = uiPlatform;
	}
	public List<String> getGw() {
		return gw;
	}
	public void setGw(List<String> gw) {
		this.gw = gw;
	}
	public String getRedis() {
		return redis;
	}
	public void setRedis(String redis) {
		this.redis = redis;
	}
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