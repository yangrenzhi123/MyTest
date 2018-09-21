package com.zyxk.sevice.check;

public class PConfig {

	private Long frequency;
	private Processes processes;
	private Integer satrt;

	public Integer getSatrt() {
		return satrt;
	}

	public void setSatrt(Integer satrt) {
		this.satrt = satrt;
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