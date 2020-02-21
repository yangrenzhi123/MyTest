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
	private List<String> zk;
	private List<String> canal;
	private List<String> device;
	private List<String> dao;
	private List<String> ca;
	private List<String> third;
	private List<String> disk;
	private List<String> dc;
	private List<String> fl;
	private List<String> file;
	private List<String> nms;
	private List<String> etl;
	private List<String> cs;
	private List<String> canalClient;
	private List<String> console;
	private List<String> scheduler;
	private List<String> platform;
	private List<String> mongodbs;
	private String dingDingToken;

	public List<String> getDevice() {
		return device;
	}
	public void setDevice(List<String> device) {
		this.device = device;
	}
	public List<String> getThird() {
		return third;
	}
	public void setThird(List<String> third) {
		this.third = third;
	}
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
	public List<String> getDisk() {
		return disk;
	}
	public void setDisk(List<String> disk) {
		this.disk = disk;
	}
	public List<String> getDao() {
		return dao;
	}
	public void setDao(List<String> dao) {
		this.dao = dao;
	}
	public List<String> getCa() {
		return ca;
	}
	public void setCa(List<String> ca) {
		this.ca = ca;
	}
	public List<String> getDc() {
		return dc;
	}
	public void setDc(List<String> dc) {
		this.dc = dc;
	}
	public List<String> getFl() {
		return fl;
	}
	public void setFl(List<String> fl) {
		this.fl = fl;
	}
	public List<String> getFile() {
		return file;
	}
	public void setFile(List<String> file) {
		this.file = file;
	}
	public List<String> getNms() {
		return nms;
	}
	public void setNms(List<String> nms) {
		this.nms = nms;
	}
	public List<String> getScheduler() {
		return scheduler;
	}
	public void setScheduler(List<String> scheduler) {
		this.scheduler = scheduler;
	}
	public List<String> getPlatform() {
		return platform;
	}
	public void setPlatform(List<String> platform) {
		this.platform = platform;
	}
	public List<String> getEtl() {
		return etl;
	}
	public void setEtl(List<String> etl) {
		this.etl = etl;
	}
	public List<String> getCs() {
		return cs;
	}
	public void setCs(List<String> cs) {
		this.cs = cs;
	}
	public List<String> getCanal() {
		return canal;
	}
	public void setCanal(List<String> canal) {
		this.canal = canal;
	}
	public List<String> getCanalClient() {
		return canalClient;
	}
	public void setCanalClient(List<String> canalClient) {
		this.canalClient = canalClient;
	}
	public List<String> getConsole() {
		return console;
	}
	public void setConsole(List<String> console) {
		this.console = console;
	}
	public List<String> getZk() {
		return zk;
	}
	public void setZk(List<String> zk) {
		this.zk = zk;
	}
	public List<String> getMongodbs() {
		return mongodbs;
	}
	public void setMongodbs(List<String> mongodbs) {
		this.mongodbs = mongodbs;
	}
	public String getDingDingToken() {
		return dingDingToken;
	}
	public void setDingDingToken(String dingDingToken) {
		this.dingDingToken = dingDingToken;
	}
}