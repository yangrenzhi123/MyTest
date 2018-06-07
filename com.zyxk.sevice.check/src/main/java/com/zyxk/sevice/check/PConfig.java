package com.zyxk.sevice.check;

public class PConfig {

	private Long frequency;
	private Processes processes;
	private Integer suspend;

	public Integer getSuspend() {
		return suspend;
	}

	public void setSuspend(Integer suspend) {
		this.suspend = suspend;
	}

	public Long getFrequency() {
		return frequency;
	}

	public void setFrequency(Long frequency) {
		this.frequency = frequency;
	}

	public Processes getProcesses() {
		return processes;
	}

	public void setProcesses(Processes processes) {
		this.processes = processes;
	}
}