package com.zyxk.sevice.check;

public class PConfig {

	private Long frequency;
	private Processes processes;
	private Integer start;

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
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