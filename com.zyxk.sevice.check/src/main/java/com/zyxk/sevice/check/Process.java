package com.zyxk.sevice.check;

public class Process {

	private String ip;
	private Integer port;
	private String exec;
	private Long execTime;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public String getExec() {
		return exec;
	}

	public void setExec(String exec) {
		this.exec = exec;
	}

	public Long getExecTime() {
		return execTime;
	}

	public void setExecTime(Long execTime) {
		this.execTime = execTime;
	}

	@Override
	public String toString() {
		return "Process [port=" + port + "]";
	}
}